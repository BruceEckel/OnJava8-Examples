// threads/Summing3.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

public class Summing3 {
  static long basicSum(Long[] ia) {
    long sum = 0;
    final int sz = ia.length;
    for(int i = 0; i < sz; i++)
      sum += ia[i];
    return sum;
  }
  // Approximate largest value of SZ before
  // running out of memory on my machine:
  public static int SZ = 10_000_000;
  public static final long CHECK =
    (long)SZ * ((long)SZ + 1)/2;
  public static void main(String[] args) {
    System.out.println(CHECK);
    Long[] La = new Long[SZ+1];
    Arrays.parallelSetAll(La, i -> (long)i);
    Summing.timeTest("Long Array Stream Reduce",
      CHECK, () ->
      Arrays.stream(La).reduce(0L, Long::sum));
    Summing.timeTest("Long Basic Sum", CHECK, () ->
      basicSum(La));
    // Destructive summation:
    Summing.timeTest("Long parallelPrefix",CHECK, ()-> {
      Arrays.parallelPrefix(La, Long::sum);
      return La[La.length - 1];
    });
  }
}
/* Output:
50000005000000
Long Array Stream Reduce: 1046ms
Long Basic Sum: 21ms
Long parallelPrefix: 3287ms
*/
