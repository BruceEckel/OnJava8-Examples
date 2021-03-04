// patterns/trash/Paper.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package patterns.trash;

public class Paper extends Trash {
  public Paper(double wt) { super(wt); }
  @Override public double price() {
    return Price.PAPER;
  }
  // Ignore for now; to be used later:
  @Override public void accept(Visitor v) {
    v.visit(this);
  }
}
