// patterns/trash/Glass.java
// ©2016 MindView LLC: see Copyright.txt
package patterns.trash;

public class Glass extends Trash {
  private static double val = 0.23f;
  public Glass(double wt) { super(wt); }
  @Override
  public double value() { return val; }
  public static void value(double newVal) {
    val = newVal;
  }
}
