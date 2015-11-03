// generics/RandomList.java
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
