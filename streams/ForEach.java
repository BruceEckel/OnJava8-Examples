// streams/ForEach.java
import java.util.*;
import java.util.stream.*;
import static streams.RandInts.*;

public class ForEach {
  final static int sz = 5;
  public static void main(String[] args) {
    rands().limit(sz)
      .forEach(System.out::println);

    System.out.println(" -----");
    rands().limit(sz)
      .parallel()
      .forEach(System.out::println);

    System.out.println(" -----");
    rands().limit(sz)
      .parallel()
      .forEachOrdered(System.out::println);
  }
}
/* Output:
258
555
693
861
961
 -----
861
961
258
693
555
 -----
258
555
693
861
961
*/
