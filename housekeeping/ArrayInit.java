// housekeeping/ArrayInit.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Array initialization.
import java.util.*;

public class ArrayInit {
  public static void main(String[] args) {
    Integer[] a = {
      1, 2,
      3, // Autoboxing
    };
    Integer[] b = new Integer[]{
      1, 2,
      3, // Autoboxing
    };
    System.out.println(Arrays.toString(a));
    System.out.println(Arrays.toString(b));
  }
}
/* Output:
[1, 2, 3]
[1, 2, 3]
*/
