//: patterns/PaperScissorsRock.java
// Demonstration of multiple dispatching.
import java.util.*;

// An enumeration type:
class Outcome {
  private int value;
  private Outcome(int val) { value = val; }
  public final static Outcome
    WIN = new Outcome(0), 
    LOSE = new Outcome(1), 
    DRAW = new Outcome(2);
  @Override
  public String toString() {
    switch(value) {
      default:
      case 0: return "win";
      case 1: return "lose";
      case 2: return "draw";
    }
  }
  @Override
  public boolean equals(Object o) {
    return (o instanceof Outcome)
      && (value == ((Outcome)o).value);
  }
}

interface Item {
  Outcome compete(Item it);
  Outcome eval(Paper p);
  Outcome eval(Scissors s);
  Outcome eval(Rock r);
}

class Paper implements Item {
  @Override
  public Outcome compete(Item it) {
    return it.eval(this);
  }
  @Override
  public Outcome eval(Paper p) {
    return Outcome.DRAW;
  }
  @Override
  public Outcome eval(Scissors s) {
    return Outcome.WIN;
  }
  @Override
  public Outcome eval(Rock r) {
    return Outcome.LOSE;
  }
  @Override
  public String toString() { return "Paper"; }
}

class Scissors implements Item {
  @Override
  public Outcome compete(Item it) {
    return it.eval(this);
  }
  @Override
  public Outcome eval(Paper p) {
    return Outcome.LOSE;
  }
  @Override
  public Outcome eval(Scissors s) {
    return Outcome.DRAW;
  }
  @Override
  public Outcome eval(Rock r) {
    return Outcome.WIN;
  }
  @Override
  public String toString() { return "Scissors"; }
}

class Rock implements Item {
  @Override
  public Outcome compete(Item it) {
    return it.eval(this);
  }
  @Override
  public Outcome eval(Paper p) {
    return Outcome.WIN;
  }
  @Override
  public Outcome eval(Scissors s) {
    return Outcome.LOSE;
  }
  @Override
  public Outcome eval(Rock r) {
    return Outcome.DRAW;
  }
  @Override
  public String toString() { return "Rock"; }
}

class ItemFactory {
  public static Item newItem() {
    switch((int)(Math.random() * 3)) {
      default:
      case 0:
        return new Scissors();
      case 1:
        return new Paper();
      case 2:
        return new Rock();
    }
  }
}

class Compete {
  public static Outcome match(Item a, Item b) {
    System.out.print(a + " <--> " + b + " : ");
    return a.compete(b);
  }
}

public class PaperScissorsRock {
  public static void main(String args[]) {
    ArrayList items = new ArrayList();
    for(int i = 0; i < 40; i++)
      items.add(ItemFactory.newItem());
    for(int i = 0; i < items.size()/2; i++)
      System.out.println(
        Compete.match(
          (Item)items.get(i), 
          (Item)items.get(i*2)));
  }
} ///:~
