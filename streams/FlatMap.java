// streams/FlatMap.java
import java.util.stream.*;

public class FlatMap {
  public static void main(String[] args) {
    Stream.of(1, 2, 3)
      .flatMap(i -> Stream.of("Gonzo", "Fozzie", "Beaker"))
      .forEach(System.out::println);
  }
}
/* Output:
Gonzo
Fozzie
Beaker
Gonzo
Fozzie
Beaker
Gonzo
Fozzie
Beaker
*/
