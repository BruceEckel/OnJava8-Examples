// patterns/visitor/BeeAndFlowers.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstration of "visitor" pattern.
package patterns.visitor;
import java.util.*;

interface Visitor {
  void visit(Gladiolus g);
  void visit(Renuculus r);
  void visit(Chrysanthemum c);
}

// The Flower hierarchy cannot be changed:
interface Flower {
  void accept(Visitor v);
}

class Gladiolus implements Flower {
  @Override
  public void accept(Visitor v) { v.visit(this);}
}

class Renuculus implements Flower {
  @Override
  public void accept(Visitor v) { v.visit(this);}
}

class Chrysanthemum implements Flower {
  @Override
  public void accept(Visitor v) { v.visit(this);}
}

// Add the ability to produce a string:
class StringVal implements Visitor {
  String s;
  @Override
  public String toString() { return s; }
  @Override
  public void visit(Gladiolus g) {
    s = "Gladiolus";
  }
  @Override
  public void visit(Renuculus r) {
    s = "Renuculus";
  }
  @Override
  public void visit(Chrysanthemum c) {
    s = "Chrysanthemum";
  }
}

// Add the ability to do "Bee" activities:
class Bee implements Visitor {
  @Override
  public void visit(Gladiolus g) {
    System.out.println("Bee and Gladiolus");
  }
  @Override
  public void visit(Renuculus r) {
    System.out.println("Bee and Renuculus");
  }
  @Override
  public void visit(Chrysanthemum c) {
    System.out.println("Bee and Chrysanthemum");
  }
}

class FlowerFactory {
  public static Flower newFlower() {
    switch((int)(Math.random() * 3)) {
      default:
      case 0: return new Gladiolus();
      case 1: return new Renuculus();
      case 2: return new Chrysanthemum();
    }
  }
}

public class BeeAndFlowers {
  public static void main(String args[]) {
    List<Flower> flowers = new ArrayList<>();
    for(int i = 0; i < 10; i++)
      flowers.add(FlowerFactory.newFlower());
    StringVal sval = new StringVal();
    Iterator<Flower> it = flowers.iterator();
    while(it.hasNext()) {
      it.next().accept(sval);
      System.out.println(sval);
    }
    // Perform "Bee" operation on all Flowers:
    Bee bee = new Bee();
    it = flowers.iterator();
    while(it.hasNext())
      it.next().accept(bee);
  }
}
/* Output:
Gladiolus
Chrysanthemum
Chrysanthemum
Renuculus
Chrysanthemum
Gladiolus
Renuculus
Renuculus
Gladiolus
Renuculus
Bee and Gladiolus
Bee and Chrysanthemum
Bee and Chrysanthemum
Bee and Renuculus
Bee and Chrysanthemum
Bee and Gladiolus
Bee and Renuculus
Bee and Renuculus
Bee and Gladiolus
Bee and Renuculus
*/
