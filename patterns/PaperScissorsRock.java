// patterns/PaperScissorsRock.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstration of multiple dispatching.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import enums.Outcome;
import static enums.Outcome.*;
import enums.Item;
import enums.Paper;
import enums.Scissors;
import enums.Rock;
import onjava.*;
import static onjava.Tuple.*;

class ItemFactory {
  static List<Supplier<Item>> items =
    Arrays.asList(
      Scissors::new, Paper::new, Rock::new);
  static final int SZ = items.size();
  private static SplittableRandom rand =
    new SplittableRandom(47);
  public static Item newItem() {
    return items.get(rand.nextInt(SZ)).get();
  }
  public static Tuple2<Item,Item> newPair() {
    return tuple(newItem(), newItem());
  }
}

class Compete {
  public static Outcome match(Tuple2<Item,Item> p) {
    System.out.print(p.a1 + " -> " + p.a2 + " : ");
    return p.a1.compete(p.a2);
  }
}

public class PaperScissorsRock {
  public static void main(String[] args) {
    Stream.generate(ItemFactory::newPair)
      .limit(20)
      .map(Compete::match)
      .forEach(System.out::println);
  }
}
/* Output:
Scissors -> Rock : LOSE
Scissors -> Paper : WIN
Rock -> Paper : LOSE
Rock -> Rock : DRAW
Rock -> Paper : LOSE
Paper -> Scissors : LOSE
Rock -> Paper : LOSE
Scissors -> Scissors : DRAW
Scissors -> Rock : LOSE
Scissors -> Paper : WIN
Scissors -> Rock : LOSE
Paper -> Scissors : LOSE
Rock -> Rock : DRAW
Scissors -> Scissors : DRAW
Paper -> Paper : DRAW
Scissors -> Paper : WIN
Scissors -> Rock : LOSE
Scissors -> Paper : WIN
Rock -> Paper : LOSE
Rock -> Scissors : WIN
*/
