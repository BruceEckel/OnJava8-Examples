// streams/CollectionToStream.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;

public class CollectionToStream {
  public static void main(String[] args) {
    List<Bubble> bubbles = Arrays.asList(
      new Bubble(1), new Bubble(2), new Bubble(3));
    System.out.println(
      bubbles.stream()
        .mapToInt(b -> b.i)
        .sum());

    Set<String> w = new HashSet<>(Arrays.asList(
      "It's a wonderful day for pie!".split(" ")));
    w.stream()
      .map(x -> x + " ")
      .forEach(System.out::print);
    System.out.println();

    Map<String, Double> m = new HashMap<>();
    m.put("pi", 3.14159);
    m.put("e", 2.718);
    m.put("phi", 1.618);
    m.entrySet().stream()
      .map(e -> e.getKey() + ": " + e.getValue())
      .forEach(System.out::println);
  }
}
/* Output:
6
a pie! It's for wonderful day
phi: 1.618
e: 2.718
pi: 3.14159
*/

// My simulation code
class CollectionToStreamTest {
  public static void main(String[] args) {
    List<Employee> list = Arrays.asList(
        new Employee(1), new Employee(2), new Employee(3));
    System.out.println(list.stream()
        .mapToLong(e -> e.id * 10)
        .sum());

    Set<String> set = new HashSet<>(Arrays.asList(
        "I have a dream !".split(" ")));
    set.stream()
        .map(x -> x + " ")
        .forEach(System.out::print);
    System.out.println();

    Map<String, Integer> map = new HashMap<>();
    map.put("China", 1);
    map.put("America", 2);
    map.put("Japan", 3);
    map.entrySet().stream()
        .map(e -> e.getKey() + " : " + e.getValue())
        .forEach(System.out::println);
  }
}
