// streams/Randoms.java
import java.util.*;

public class Randoms {
  public static void main(String[] args) {
    new Random(47)
      .ints(5, 20)
      .distinct()
      .limit(7)
      .sorted()
      .forEach(System.out::println);
  }
}
/* Output:
6
10
13
16
17
18
19
*/
