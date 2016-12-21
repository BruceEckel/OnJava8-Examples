// concurrent/RandomWork.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import onjava.Nap;

public class RandomWork {
  private static SplittableRandom rand =
    new SplittableRandom(47);
  private final int id;
  public RandomWork(int id) { this.id = id; }
  public static RandomWork work(RandomWork r) {
    new Nap(rand.nextInt(250));
    System.out.println(r);
    return r;
  }
  @Override
  public String toString() {
    return "RandomWork " + id;
  }
}
