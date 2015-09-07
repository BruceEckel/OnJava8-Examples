// patterns/recyclea/RecycleA.java
// ©2015 MindView LLC: see Copyright.txt
// Recycling with RTTI.
package patterns.recyclea;
import java.util.*;

abstract class Trash {
  private double weight;
  Trash(double wt) { weight = wt; }
  abstract double value();
  double weight() { return weight; }
  // Sums the value of Trash in a bin:
  static void sumValue(List<? extends Trash> bin) {
    double val = 0.0f;
    for(Trash t : bin) {
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
    List<Glass> glassBin = new ArrayList<>();
    List<Paper> paperBin = new ArrayList<>();
    List<Aluminum> alBin = new ArrayList<>();
    // Sort the Trash:
    for(Trash t : bin) {
      // RTTI to discover Trash type:
      if(t instanceof Aluminum)
        alBin.add((Aluminum)t);
      if(t instanceof Paper)
        paperBin.add((Paper)t);
      if(t instanceof Glass)
        glassBin.add((Glass)t);
    }
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
}
/* Output: (First and last 10 Lines)
weight of patterns.recyclea.Aluminum = 97.49879461494744
weight of patterns.recyclea.Aluminum = 99.60561944523387
weight of patterns.recyclea.Aluminum = 93.10883063826067
weight of patterns.recyclea.Aluminum = 21.376144761865177
weight of patterns.recyclea.Aluminum = 79.57301730061971
weight of patterns.recyclea.Aluminum = 18.397954995447552
weight of patterns.recyclea.Aluminum = 81.2801150163881
weight of patterns.recyclea.Aluminum = 55.39736045734065
weight of patterns.recyclea.Aluminum = 6.284334985288654
Total value = 922.7120038880252
________...________...________...________...________
weight of patterns.recyclea.Paper = 59.58008361440512
weight of patterns.recyclea.Paper = 23.177907462321667
weight of patterns.recyclea.Aluminum = 81.2801150163881
weight of patterns.recyclea.Aluminum = 55.39736045734065
weight of patterns.recyclea.Aluminum = 6.284334985288654
weight of patterns.recyclea.Paper = 34.39090015475562
weight of patterns.recyclea.Glass = 50.356208994386385
weight of patterns.recyclea.Paper = 89.7955125865738
weight of patterns.recyclea.Paper = 32.73979803583131
Total value = 1101.1015429445522
*/
