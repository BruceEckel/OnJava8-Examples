// patterns/trashvisitor/TrashVisitor.java
// ©2015 MindView LLC: see Copyright.txt
// The "visitor" pattern.
package patterns.trashvisitor;
import patterns.trash.*;
import java.util.*;
import static com.mindviewinc.util.Print.*;

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
    ArrayList<Trash> bin = new ArrayList<>();
    // ParseTrash still works, without changes:
    ParseTrash.fillBin("trashvisitor", "VTrash.dat", bin);
    // You could even iterate through
    // a list of visitors!
    PriceVisitor pv = new PriceVisitor();
    WeightVisitor wv = new WeightVisitor();
    for(Trash aBin : bin) {
      Visitable v = (Visitable) aBin;
      v.accept(pv);
      v.accept(wv);
    }
    pv.total();
    wv.total();
  }
}
/* Output: (First and last 10 Lines)
Loading patterns.trashvisitor.VGlass
Loading patterns.trashvisitor.VPaper
Loading patterns.trashvisitor.VAluminum
Loading patterns.trashvisitor.VCardboard
value of Glass= 12.420000225305557
Glass weight = 54.0
value of Paper= 2.2000000327825546
Paper weight = 22.0
value of Paper= 1.1000000163912773
Paper weight = 11.0
________...________...________...________...________
value of Cardboard = 5.060000091791153
Cardboard weight = 22.0
Total Aluminum: $860.0499778985977
Total Paper: $35.80000053346157
Total Glass: $150.1900027245283
Total Cardboard: $40.02000072598457
Total weight Aluminum:515.0
Total weight Paper:358.0
Total weight Glass:653.0
Total weight Cardboard:174.0
*/
