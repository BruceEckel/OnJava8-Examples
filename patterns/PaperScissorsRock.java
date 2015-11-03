// patterns/PaperScissorsRock.java
// Demonstration of multiple dispatching.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

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

class ItemPair {
  Item first;
  Item second;
  ItemPair(Item f, Item s) {
    first = f;
    second = s;
  }
}

class ItemFactory {
  static List<Supplier<Item>> items =
    Arrays.asList(
      Scissors::new, Paper::new, Rock::new);
  final static int sz = items.size();
  private static Random rand = new Random(47);
  public static Item newItem() {
    return items.get(rand.nextInt(sz)).get();
  }
  public static ItemPair newPair() {
    return new ItemPair(newItem(), newItem());
  }
}

class Compete {
  public static Outcome match(ItemPair p) {
    System.out.print(
      p.first + " <--> " + p.second + " : ");
    return p.first.compete(p.second);
  }
}

public class PaperScissorsRock {
  public static void main(String args[]) {
    Stream.generate(ItemFactory::newPair)
      .limit(20)
      .map(Compete::match)
      .forEach(System.out::println);
  }
}
/* Output:
Rock <--> Rock : DRAW
Paper <--> Rock : WIN
Paper <--> Rock : WIN
Paper <--> Rock : WIN
Scissors <--> Paper : WIN
Scissors <--> Scissors : DRAW
Scissors <--> Paper : WIN
Rock <--> Paper : LOSE
Paper <--> Paper : DRAW
Rock <--> Paper : LOSE
Paper <--> Scissors : LOSE
Paper <--> Scissors : LOSE
Rock <--> Scissors : WIN
Rock <--> Paper : LOSE
Paper <--> Rock : WIN
Scissors <--> Paper : WIN
Paper <--> Scissors : LOSE
Paper <--> Scissors : LOSE
Paper <--> Scissors : LOSE
Paper <--> Scissors : LOSE
*/
