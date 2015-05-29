//: patterns/trash/Cardboard.java 
// ©2015 MindView LLC: see Copyright.txt
// The Cardboard class with prototyping.
package patterns.trash;

public class Cardboard extends Trash {
  private static double val = 0.23f;
  public Cardboard(double wt) { super(wt); }
  @Override
  public double value() { return val; }
  public static void value(double newVal) {
    val = newVal;
  }
} ///:~
