// patterns/recyclea/RecycleA.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Recycling with RTTI
package patterns.recyclea;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

abstract class Trash {
  double weight;
  Trash(double wt) { weight = wt; }
  abstract double value();
  // Sums the value of Trash in a bin:
  private static double val;
  static void sumValue(List<? extends Trash> bin) {
    val = 0.0f;
    bin.forEach( t -> {
      // Polymorphism in action:
      val += t.weight * t.value();
      System.out.println(
        "weight of " +
        // Using RTTI to get type
        // information about the class:
        t.getClass().getSimpleName() +
        " = " + t.weight);
    });
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

class TrashFactory {
  static List<Function<Double, Trash>> ttypes =
    Arrays.asList(
      Aluminum::new, Paper::new, Glass::new);
  final static int sz = ttypes.size();
  private static SplittableRandom rand = new SplittableRandom(47);
  public static Trash newTrash() {
    return ttypes
      .get(rand.nextInt(sz))
      .apply(rand.nextDouble());
  }
}

public class RecycleA {
  public static void main(String[] args) {
    List<Trash> bin =
      Stream.generate(TrashFactory::newTrash)
        .limit(25)
        .collect(Collectors.toList());
    List<Glass> glassBin = new ArrayList<>();
    List<Paper> paperBin = new ArrayList<>();
    List<Aluminum> alBin = new ArrayList<>();
    // Sort the Trash:
    bin.forEach( t -> {
      // RTTI to discover Trash type:
      if(t instanceof Aluminum)
        alBin.add((Aluminum)t);
      if(t instanceof Paper)
        paperBin.add((Paper)t);
      if(t instanceof Glass)
        glassBin.add((Glass)t);
    });
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
}
/* Output: (First and last 11 Lines)
weight of patterns.recyclea.Aluminum = 0.11435456649761422
weight of patterns.recyclea.Aluminum = 0.5295954256745989
weight of patterns.recyclea.Aluminum = 0.44032876173820623
weight of patterns.recyclea.Aluminum = 0.483968447804611
weight of patterns.recyclea.Aluminum = 0.2724064060083268
weight of patterns.recyclea.Aluminum = 0.7661553155473436
weight of patterns.recyclea.Aluminum = 0.32266202529378485
weight of patterns.recyclea.Aluminum = 0.29010681217024337
weight of patterns.recyclea.Aluminum = 0.04867885164993724
weight of patterns.recyclea.Aluminum = 0.6398064631177899
Total value = 6.526465168373229
________...________...________...________...________
weight of patterns.recyclea.Paper = 0.7024254510631527
weight of patterns.recyclea.Paper = 0.7775491010186331
weight of patterns.recyclea.Paper = 0.5929413550962656
weight of patterns.recyclea.Paper = 0.8991222558891441
weight of patterns.recyclea.Aluminum = 0.32266202529378485
weight of patterns.recyclea.Aluminum = 0.29010681217024337
weight of patterns.recyclea.Glass = 0.42812712031823896
weight of patterns.recyclea.Paper = 0.8242175461669214
weight of patterns.recyclea.Aluminum = 0.04867885164993724
weight of patterns.recyclea.Aluminum = 0.6398064631177899
Total value = 7.489772197549787
*/
