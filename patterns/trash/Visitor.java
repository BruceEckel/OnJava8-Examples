// patterns/trash/Visitor.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The base class for Visitors.
package patterns.trash;

public abstract class Visitor {
  protected double alTotal; // Aluminum
  protected double pTotal;  // Paper
  protected double gTotal;  // Glass
  protected double cTotal;  // Cardboard
  protected String descriptor;
  protected Visitor(String descriptor) {
    this.descriptor = descriptor;
  }
  protected void show(String type, double value) {
    System.out.printf(
      "%s %s: %.2f%n", type, descriptor, value);
  }
  public void total() {
    show("Total Aluminum", alTotal);
    show("Total Paper", pTotal);
    show("Total Glass", gTotal);
    show("Total Cardboard", cTotal);
  }
  abstract void visit(Aluminum a);
  abstract void visit(Paper p);
  abstract void visit(Glass g);
  abstract void visit(Cardboard c);
}
