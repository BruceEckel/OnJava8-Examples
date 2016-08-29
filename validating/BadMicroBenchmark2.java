// validating/BadMicroBenchmark2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Reversing the test order
import java.util.*;

public class BadMicroBenchmark2 {
  static final int SIZE = 20_000_000;
  public static void main(String[] args) {
    long[] la = new long[SIZE];
    System.out.print("parallelSetAll: ");
    Time.it(() -> Arrays.parallelSetAll(la, n -> n));
    System.out.print("setAll: ");
    Time.it(() -> Arrays.setAll(la, n -> n));
  }
}
/* Output:
parallelSetAll: 68
setAll: 196
*/
