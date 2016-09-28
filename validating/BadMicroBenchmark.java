// validating/BadMicroBenchmark.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;

public class BadMicroBenchmark {
  static final int SIZE = 250_000_000;
  public static void main(String[] args) {
    long[] la = new long[SIZE];
    System.out.print("setAll: ");
    Time.it(() -> Arrays.setAll(la, n -> n));
    System.out.print("parallelSetAll: ");
    Time.it(() -> Arrays.parallelSetAll(la, n -> n));
  }
}
/* Output:
setAll: 272
parallelSetAll: 301
*/
