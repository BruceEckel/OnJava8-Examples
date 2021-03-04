// reflection/ID2.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.stream.*;

public class ID2 {
  private static long counter;
  private final long id = counter++;
  @Override public String toString() {
    return Long.toString(id);
  }
  public static void main(String[] args) {
    Stream.generate(
      new DynamicSupplier<>(ID2.class))
      .skip(10)
      .limit(5)
      .forEach(System.out::println);
  }
}
/* Output:
10
11
12
13
14
*/
