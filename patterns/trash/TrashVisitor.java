// patterns/trash/TrashVisitor.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Related algorithms packaged in
// each implementation of Visitor.
// {java patterns.trash.TrashVisitor}
package patterns.trash;
import java.util.*;

class PriceVisitor extends Visitor {
  public PriceVisitor() { super("price"); }
  @Override public void visit(Aluminum al) {
    double price = al.weight * al.price();
    show("Aluminum", price);
    alTotal += price;
  }
  @Override public void visit(Paper p) {
    double price = p.weight * p.price();
    show("Paper", price);
    pTotal += price;
  }
  @Override public void visit(Glass g) {
    double price = g.weight * g.price();
    show("Glass", price);
    gTotal += price;
  }
  @Override public void visit(Cardboard c) {
    double price = c.weight * c.price();
    show("Cardboard", price);
    cTotal += price;
  }
}

class WeightVisitor extends Visitor {
  public WeightVisitor() { super("weight"); }
  @Override public void visit(Aluminum al) {
    show("Aluminum", al.weight);
    alTotal += al.weight;
  }
  @Override public void visit(Paper p) {
    show("Paper", p.weight);
    pTotal += p.weight;
  }
  @Override public void visit(Glass g) {
    show("Glass", g.weight);
    gTotal += g.weight;
  }
  @Override public void visit(Cardboard c) {
    show("Cardboard", c.weight);
    cTotal += c.weight;
  }
}

public class TrashVisitor {
  public static void main(String[] args) {
    List<Trash> bin = new ArrayList<>();
    ParseTrash.fillBin("trash", bin);
    List<Visitor> visitors = Arrays.asList(
      new PriceVisitor(), new WeightVisitor());
    bin.forEach(
      trash -> visitors.forEach(trash::accept)
    );
    visitors.forEach(Visitor::total);
  }
}
/* Output:
Loading patterns.trash.Cardboard
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Glass
Cardboard price: 0.48
Cardboard weight: 4.40
Paper price: 0.80
Paper weight: 8.00
Aluminum price: 3.01
Aluminum weight: 1.80
Glass price: 1.24
Glass weight: 5.40
Aluminum price: 5.68
Aluminum weight: 3.40
Cardboard price: 0.24
Cardboard weight: 2.20
Glass price: 0.99
Glass weight: 4.30
Cardboard price: 0.13
Cardboard weight: 1.20
Paper price: 0.66
Paper weight: 6.60
Aluminum price: 4.51
Aluminum weight: 2.70
Paper price: 0.91
Paper weight: 9.10
Glass price: 0.83
Glass weight: 3.60
Total Aluminum price: 13.19
Total Paper price: 2.37
Total Glass price: 3.06
Total Cardboard price: 0.86
Total Aluminum weight: 7.90
Total Paper weight: 23.70
Total Glass weight: 13.30
Total Cardboard weight: 7.80
*/
