// threads/Summing4.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

public class Summing4 {
  public static void main(String[] args) {
    System.out.println(Summing3.CHECK);
    Long[] La = new Long[Summing3.SZ+1];
    Arrays.parallelSetAll(La, i -> (long)i);
    Summing.timeTest("Long Parallel",
      Summing3.CHECK, () ->
      Arrays.stream(La).parallel().reduce(0L,Long::sum));
  }
}
/* Output:
50000005000000
Long Parallel: 1014ms
*/
