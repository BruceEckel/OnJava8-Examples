// arrays/ParallelSort.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.time.*;
import onjava.*;
import static onjava.TimeIt.*;

public class ParallelSort {
  static final int SZ = 10_000_000;
  public static void main(String[] args) {
    int[] ia1 = new Rand.int_().array(SZ);
    int[] ia2 = Arrays.copyOf(ia1, ia1.length);
    System.out.print("sort(): ");
    long millis1 = timeIt(() -> Arrays.sort(ia1));
    System.out.print("parallelSort(): ");
    long millis2 = timeIt(() -> Arrays.parallelSort(ia2));
    System.out.println(millis1/millis2);
  }
}
/* Output:
sort(): 864
parallelSort(): 274
3
*/
