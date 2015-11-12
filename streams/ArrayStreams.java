// streams/ArrayStreams.java
import java.util.*;
import java.util.stream.*;

public class ArrayStreams {
  public static void main(String[] args) {
    Arrays.stream(new double[] { 3.14159, 2.718, 1.618 })
      .forEach(System.out::println);
    Arrays.stream(new int[] { 1, 3, 5 })
      .forEach(System.out::println);
    Arrays.stream(new long[] { 11, 22, 44, 66 })
      .forEach(System.out::println);
    // Select a subrange:
    Arrays.stream(
      new int[] { 1, 3, 5, 7, 15, 28, 37 }, 3, 6)
      .forEach(System.out::println);
  }
}
/* Output:
3.14159
2.718
1.618
1
3
5
7
15
28
37
11
22
44
66
7
15
28
*/
