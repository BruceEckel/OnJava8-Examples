//: patterns/trash/Paper.java 
// The Paper class with prototyping.
package patterns.trash;

public class Paper extends Trash {
  private static double val = 0.10f;
  public Paper(double wt) { super(wt); }
  @Override
  public double value() { return val; }
  public static void value(double newVal) {
    val = newVal;
  }
} ///:~
