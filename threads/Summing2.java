// threads/Summing2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

public class Summing2 {
  static long basicSum(long[] ia) {
    long sum = 0;
    final int sz = ia.length;
    for(int i = 0; i < sz; i++)
      sum += ia[i];
    return sum;
  }
  // Approximate largest value of SZ before
  // running out of memory on my machine:
  public static int SZ = 20_000_000;
  public static final long CHECK =
    (long)SZ * ((long)SZ + 1)/2;
  public static void main(String[] args) {
    System.out.println(CHECK);
    long[] la = new long[SZ+1];
    Arrays.parallelSetAll(la, i -> i);
    Summing.timeTest("Array Stream Sum", CHECK, () ->
      Arrays.stream(la).sum());
    Summing.timeTest("Parallel", CHECK, () ->
      Arrays.stream(la).parallel().sum());
    Summing.timeTest("Basic Sum", CHECK, () ->
      basicSum(la));
    // Destructive summation:
    Summing.timeTest("parallelPrefix", CHECK, () -> {
      Arrays.parallelPrefix(la, Long::sum);
      return la[la.length - 1];
    });
  }
}
/* Output:
200000010000000
Array Stream Sum: 111ms
Parallel: 26ms
Basic Sum: 33ms
parallelPrefix: 50ms
*/
