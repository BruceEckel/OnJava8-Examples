//: patterns/PaperScissorsRock.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstration of multiple dispatching.
import java.util.*;

enum Outcome { WIN, LOSE, DRAW }

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
    ArrayList<Item> items = new ArrayList<>();
    for(int i = 0; i < 40; i++)
      items.add(ItemFactory.newItem());
    for(int i = 0; i < items.size()/2; i++)
      System.out.println(
        Compete.match(
          items.get(i), items.get(i*2)));
  }
} /* Output:
Paper <--> Paper : DRAW
Paper <--> Rock : WIN
Rock <--> Scissors : WIN
Paper <--> Scissors : LOSE
Scissors <--> Rock : LOSE
Paper <--> Rock : WIN
Scissors <--> Scissors : DRAW
Paper <--> Paper : DRAW
Rock <--> Scissors : WIN
Rock <--> Scissors : WIN
Rock <--> Scissors : WIN
Rock <--> Scissors : WIN
Scissors <--> Paper : WIN
Rock <--> Rock : DRAW
Paper <--> Paper : DRAW
Scissors <--> Scissors : DRAW
Scissors <--> Rock : LOSE
Rock <--> Paper : LOSE
Scissors <--> Paper : WIN
Paper <--> Rock : WIN
*///:~
