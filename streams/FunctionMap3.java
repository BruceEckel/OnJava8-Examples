// streams/FunctionMap3.java
// Producing numeric output streams
import java.util.*;
import java.util.stream.*;

class FunctionMap3 {
  public static void main(String[] args) {
    Stream.of("5", "7", "9")
      .mapToInt(Integer::parseInt)
      .forEach(System.out::println);
    Stream.of("17", "19", "23")
      .mapToLong(Long::parseLong)
      .forEach(System.out::println);
    Stream.of("17", "1.9", ".23")
      .mapToDouble(Double::parseDouble)
      .forEach(System.out::println);
  }
}
/* Output:
5
7
9
17
19
23
17.0
1.9
0.23
*/
