// generics/ListOfInt.java
// ©2015 MindView LLC: see Copyright.txt
// Autoboxing compensates for the inability to use
// primitives in generics.
import java.util.*;

public class ListOfInt {
  public static void main(String[] args) {
    List<Integer> li = new ArrayList<>();
    for(int i = 0; i < 5; i++)
      li.add(i);
    for(int i : li)
      System.out.print(i + " ");
  }
}
/* Output:
0 1 2 3 4
*/
