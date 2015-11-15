// generics/RandomList.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.stream.*;

public class RandomList<T> {
  private ArrayList<T> items = new ArrayList<>();
  private Random rand = new Random(47);
  public void add(T item) { items.add(item); }
  public T select() {
    return items.get(rand.nextInt(items.size()));
  }
  public static void main(String[] args) {
    RandomList<String> rs = new RandomList<>();
    Arrays.stream(
      ("The quick brown fox jumped over " +
      "the lazy brown dog").split(" "))
      .forEach(rs::add);
    IntStream.range(0, 11).forEach(i ->
      System.out.print(rs.select() + " "));
  }
}
/* Output:
brown over fox quick quick dog brown The brown lazy brown
*/
