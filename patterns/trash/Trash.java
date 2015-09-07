// patterns/trash/Trash.java
// ©2015 MindView LLC: see Copyright.txt
// Base class for Trash recycling examples.
package patterns.trash;
import java.util.*;
import java.lang.reflect.*;

public abstract class Trash {
  private double weight;
  public Trash(double wt) { weight = wt; }
  public Trash() {}
  public abstract double value();
  public double weight() { return weight; }
  // Sums the value of Trash in a bin:
  public static <T extends Trash>
  void sumValue(List<? extends T> bin) {
    Iterator<? extends T> e = bin.iterator();
    double val = 0.0f;
    while(e.hasNext()) {
      T t = e.next();
      val += t.weight() * t.value();
      System.out.println("weight of " +
        // Using RTTI to get type
        // information about the class:
        t.getClass().getName() +
        " = " + t.weight());
    }
    System.out.println("Total value = " + val);
  }
  // Remainder of class provides support for
  // prototyping:
  public static class PrototypeNotFoundException
      extends Exception {}
  public static class CannotCreateTrashException
      extends Exception {}
  private static List<Class> trashTypes =
    new ArrayList<>();
  @SuppressWarnings("unchecked")
  public static <T extends Trash> T factory(Info info)
      throws PrototypeNotFoundException,
      CannotCreateTrashException {
    for(Class trashType : trashTypes) {
      // Somehow determine the new type
      // to create, and create one:
      if(trashType.getName().contains(info.id)) {
        try {
          // Get the dynamic constructor method
          // that takes a double argument:
          Constructor ctor = trashType.getConstructor(
                  double.class);
          // Call the constructor to create a
          // new object:
          return (T)ctor.newInstance(info.data);
        } catch(NoSuchMethodException |
                SecurityException |
                InstantiationException |
                IllegalAccessException |
                IllegalArgumentException |
                InvocationTargetException ex) {
          ex.printStackTrace();
          throw new CannotCreateTrashException();
        }
      }
    }
    // Class was not in the list. Try to load it,
    // but it must be in your class path!
    try {
      System.out.println("Loading " + info.id);
      trashTypes.add(Class.forName(info.id));
    } catch(Exception e) {
      e.printStackTrace();
      throw new PrototypeNotFoundException();
    }
    // Loaded successfully. Recursive call
    // should work this time:
    return factory(info);
  }
  public static class Info {
    public String id;
    public double data;
    public Info(String name, double data) {
      id = name;
      this.data = data;
    }
  }
}
