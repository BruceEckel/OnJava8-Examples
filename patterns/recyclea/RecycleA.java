//: patterns/recyclea/RecycleA.java
// Recycling with RTTI.
package patterns.recyclea;
import java.util.*;

abstract class Trash {
  private double weight;
  Trash(double wt) { weight = wt; }
  abstract double value();
  double weight() { return weight; }
  // Sums the value of Trash in a bin:
  static void sumValue(List<Trash> bin) {
    Iterator<Trash> e = bin.iterator();
    double val = 0.0f;
    while(e.hasNext()) {
      Trash t = e.next();
      // Polymorphism in action:
      val += t.weight() * t.value();
      System.out.println(
        "weight of " +
        // Using RTTI to get type
        // information about the class:
        t.getClass().getName() +
        " = " + t.weight());
    }
    System.out.println("Total value = " + val);
  }
}

class Aluminum extends Trash {
  static double val  = 1.67f;
  Aluminum(double wt) { super(wt); }
  @Override
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

class Paper extends Trash {
  static double val = 0.10f;
  Paper(double wt) { super(wt); }
  @Override
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

class Glass extends Trash {
  static double val = 0.23f;
  Glass(double wt) { super(wt); }
  @Override
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

public class RecycleA {
  public static void main(String[] args) {
    List<Trash> bin = new ArrayList<>();
    // Fill up the Trash bin:
    for(int i = 0; i < 30; i++)
      switch((int)(Math.random() * 3)) {
        case 0 :
          bin.add(new
            Aluminum(Math.random() * 100));
          break;
        case 1 :
          bin.add(new
            Paper(Math.random() * 100));
          break;
        case 2 :
          bin.add(new
            Glass(Math.random() * 100));
      }
    List<Trash>
      glassBin = new ArrayList<>(),
      paperBin = new ArrayList<>(),
      alBin = new ArrayList<>();
    Iterator<Trash> sorter = bin.iterator();
    // Sort the Trash:
    while(sorter.hasNext()) {
      Trash t = sorter.next();
      // RTTI to show class membership:
      if(t instanceof Aluminum)
        alBin.add(t);
      if(t instanceof Paper)
        paperBin.add(t);
      if(t instanceof Glass)
        glassBin.add(t);
    }
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
} ///:~
