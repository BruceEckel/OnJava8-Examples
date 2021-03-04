// patterns/trash/TrashValue.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Sums the value of Trash in a bin.
package patterns.trash;
import java.util.*;

public class TrashValue {
  private static double total;
  public static void
  sum(List<? extends Trash> bin, String type) {
    total = 0.0;
    bin.forEach( t -> {
      System.out.println(t);
      total += t.weight * t.price();
    });
    System.out.printf(
      "Total %s value = %.2f%n", type, total);
  }
}
