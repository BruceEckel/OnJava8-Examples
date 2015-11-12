// streams/Duplicator.java
import java.util.stream.*;

public class Duplicator {
  public static void main(String[] args) {
    Stream.generate(() -> "duplicate")
      .limit(3)
      .forEach(System.out::println);
  }
}
/* Output:
duplicate
duplicate
duplicate
*/
