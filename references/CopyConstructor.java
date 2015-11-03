// references/CopyConstructor.java
// A constructor to copy an object of the same
// type, as an attempt to create a local copy.
import java.lang.reflect.*;

class FruitQualities {
  private int weight;
  private int color;
  private int firmness;
  private int ripeness;
  private int smell;
  // etc.
  // No-arg constructor:
  public FruitQualities() {
    // Do something meaningful...
  }
  // Other constructors:
  // ...
  // Copy constructor:
  public FruitQualities(FruitQualities f) {
    weight = f.weight;
    color = f.color;
    firmness = f.firmness;
    ripeness = f.ripeness;
    smell = f.smell;
    // etc.
  }
}

class Seed {
  // Members...
  public Seed() { /* No-arg constructor */ }
  public Seed(Seed s) { /* Copy constructor */ }
}

class Fruit {
  private FruitQualities fq;
  private int seeds;
  private Seed[] s;
  public Fruit(FruitQualities q, int seedCount){
    fq = q;
    seeds = seedCount;
    s = new Seed[seeds];
    for(int i = 0; i < seeds; i++)
      s[i] = new Seed();
  }
  // Other constructors:
  // ...
  // Copy constructor:
  public Fruit(Fruit f) {
    fq = new FruitQualities(f.fq);
    seeds = f.seeds;
    s = new Seed[seeds];
    // Call all Seed copy-constructors:
    for(int i = 0; i < seeds; i++)
      s[i] = new Seed(f.s[i]);
    // Other copy-construction activities...
  }
  // To allow derived constructors (or other
  // methods) to put in different qualities:
  protected void addQualities(FruitQualities q){
    fq = q;
  }
  protected FruitQualities getQualities() {
    return fq;
  }
}

class Tomato extends Fruit {
  public Tomato() {
    super(new FruitQualities(), 100);
  }
  public Tomato(Tomato t) { // Copy-constructor
    super(t); // Upcast to base copy-constructor
    // Other copy-construction activities...
  }
}

class ZebraQualities extends FruitQualities {
  private int stripedness;
  // No-arg constructor:
  public ZebraQualities() {
    super();
    // do something meaningful...
  }
  public ZebraQualities(ZebraQualities z) {
    super(z);
    stripedness = z.stripedness;
  }
}

class GreenZebra extends Tomato {
  public GreenZebra() {
    addQualities(new ZebraQualities());
  }
  public GreenZebra(GreenZebra g) {
    super(g); // Calls Tomato(Tomato)
    // Restore the right qualities:
    addQualities(new ZebraQualities());
  }
  public void evaluate() {
    ZebraQualities zq =
      (ZebraQualities)getQualities();
    // Do something with the qualities
    // ...
  }
}

public class CopyConstructor {
  public static void ripen(Tomato t) {
    // Use the "copy constructor":
    t = new Tomato(t);
    System.out.println("In ripen, t is a " +
      t.getClass().getName());
  }
  public static void slice(Fruit f) {
    f = new Fruit(f); // Hmmm... will this work?
    System.out.println("In slice, f is a " +
      f.getClass().getName());
  }
  @SuppressWarnings("unchecked")
  public static void ripen2(Tomato t) {
    try {
      Class c = t.getClass();
      // Use the "copy constructor":
      Constructor ct =
        c.getConstructor(new Class[] { c });
      Object obj =
        ct.newInstance(new Object[] { t });
      System.out.println("In ripen2, t is a " +
        obj.getClass().getName());
    } catch(NoSuchMethodException |
            SecurityException |
            InstantiationException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException e) {
      System.out.println(e);
    }
  }
  @SuppressWarnings("unchecked")
  public static void slice2(Fruit f) {
    try {
      Class c = f.getClass();
      Constructor ct =
        c.getConstructor(new Class[] { c });
      Object obj =
        ct.newInstance(new Object[] { f });
      System.out.println("In slice2, f is a " +
        obj.getClass().getName());
    } catch(NoSuchMethodException |
            SecurityException |
            InstantiationException |
            IllegalAccessException |
            IllegalArgumentException |
            InvocationTargetException e) {
      System.out.println(e);
    }
  }
  public static void main(String[] args) {
    Tomato tomato = new Tomato();
    ripen(tomato); // OK
    slice(tomato); // OOPS!
    ripen2(tomato); // OK
    slice2(tomato); // OK
    GreenZebra g = new GreenZebra();
    ripen(g); // OOPS!
    slice(g); // OOPS!
    ripen2(g); // OK
    slice2(g); // OK
    g.evaluate();
  }
}
/* Output:
In ripen, t is a Tomato
In slice, f is a Fruit
In ripen2, t is a Tomato
In slice2, f is a Tomato
In ripen, t is a Tomato
In slice, f is a Fruit
In ripen2, t is a GreenZebra
In slice2, f is a GreenZebra
*/
