// streams/Prime.java
import java.util.stream.*;
import static java.util.stream.LongStream.*;

public class Prime {
  public static boolean isPrime(long n) {
    return range(2, (long)Math.sqrt(n) + 1)
      .noneMatch(i -> n % i == 0);
  }
  public LongStream numbers() {
    return iterate(2, i -> i + 1).filter(Prime::isPrime);
  }
  public static void main(String[] args) {
    new Prime().numbers()
      .limit(100)
      .forEach(System.out::println);
  }
}
/* Output: (First and last 10 Lines)
2
3
5
7
11
13
17
19
23
29
________...________...________...________...________
467
479
487
491
499
503
509
521
523
541
*/
