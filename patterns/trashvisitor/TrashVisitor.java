//: patterns/trashvisitor/TrashVisitor.java
// The "visitor" pattern.
package patterns.trashvisitor;
import patterns.trash.*;
import java.util.*;
import static net.mindview.util.Print.*;

// Specific group of algorithms packaged
// in each implementation of Visitor:
class PriceVisitor implements Visitor {
  private double alSum; // Aluminum
  private double pSum; // Paper
  private double gSum; // Glass
  private double cSum; // Cardboard
  @Override
  public void visit(VAluminum al) {
    double v = al.weight() * al.value();
    print("value of Aluminum= " + v);
    alSum += v;
  }
  @Override
  public void visit(VPaper p) {
    double v = p.weight() * p.value();
    print("value of Paper= " + v);
    pSum += v;
  }
  @Override
  public void visit(VGlass g) {
    double v = g.weight() * g.value();
    print("value of Glass= " + v);
    gSum += v;
  }
  @Override
  public void visit(VCardboard c) {
    double v = c.weight() * c.value();
    print("value of Cardboard = " + v);
    cSum += v;
  }
  void total() {
    print(
      "Total Aluminum: $" + alSum + "\n" +
      "Total Paper: $" + pSum + "\n" +
      "Total Glass: $" + gSum + "\n" +
      "Total Cardboard: $" + cSum);
  }
}

class WeightVisitor implements Visitor {
  private double alSum; // Aluminum
  private double pSum; // Paper
  private double gSum; // Glass
  private double cSum; // Cardboard
  @Override
  public void visit(VAluminum al) {
    alSum += al.weight();
    print("Aluminum weight = " + al.weight());
  }
  @Override
  public void visit(VPaper p) {
    pSum += p.weight();
    print("Paper weight = " + p.weight());
  }
  @Override
  public void visit(VGlass g) {
    gSum += g.weight();
    print("Glass weight = " + g.weight());
  }
  @Override
  public void visit(VCardboard c) {
    cSum += c.weight();
    print("Cardboard weight = " + c.weight());
  }
  void total() {
    print("Total weight Aluminum:" + alSum);
    print("Total weight Paper:" + pSum);
    print("Total weight Glass:" + gSum);
    print("Total weight Cardboard:" + cSum);
  }
}

public class TrashVisitor {
  public static void main(String[] args) {
    ArrayList bin = new ArrayList();
    // ParseTrash still works, without changes:
    ParseTrash.fillBin("VTrash.dat", bin);
    // You could even iterate through
    // a list of visitors!
    PriceVisitor pv = new PriceVisitor();
    WeightVisitor wv = new WeightVisitor();
    Iterator it = bin.iterator();
    while(it.hasNext()) {
      Visitable v = (Visitable)it.next();
      v.accept(pv);
      v.accept(wv);
    }
    pv.total();
    wv.total();
  }
} ///:~
