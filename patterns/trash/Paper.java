// patterns/trash/Paper.java
// ©2016 MindView LLC: see Copyright.txt
package patterns.trash;

public class Paper extends Trash {
  private static double val = 0.10f;
  public Paper(double wt) { super(wt); }
  @Override
  public double value() { return val; }
  public static void value(double newVal) {
    val = newVal;
  }
}
