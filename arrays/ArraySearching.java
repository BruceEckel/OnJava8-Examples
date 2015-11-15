// arrays/ArraySearching.java
// ©2016 MindView LLC: see Copyright.txt
// Using Arrays.binarySearch().
import java.util.*;
import java.util.function.*;
import onjava.*;

public class ArraySearching {
  public static void main(String[] args) {
    Supplier<Integer> gen =
      new RandomSupplier.Integer(1000);
    int[] a = ConvertTo.primitive(
      Generated.array(new Integer[25], gen));
    Arrays.sort(a);
    System.out.println("Sorted array: " + Arrays.toString(a));
    while(true) {
      int r = gen.get();
      int location = Arrays.binarySearch(a, r);
      if(location >= 0) {
        System.out.println("Location of " + r + " is " + location +
          ", a[" + location + "] = " + a[location]);
        break; // Out of while loop
      }
    }
  }
}
/* Output:
Sorted array: [128, 140, 200, 207, 258, 258, 278, 288, 322,
429, 511, 520, 522, 551, 555, 589, 693, 704, 809, 861, 861,
868, 916, 961, 998]
Location of 322 is 8, a[8] = 322
*/
