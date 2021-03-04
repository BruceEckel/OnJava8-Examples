// patterns/trash/GroupingBy.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java patterns.trash.GroupingBy}
package patterns.trash;
import java.util.*;
import java.util.stream.*;

public class GroupingBy {
  public static void main(String[] args) {
    List<Trash> bin = new ArrayList<>();
    ParseTrash.fillBin("trash", bin);
    Map<Class, List<Trash>> m =
      bin.stream().collect(
        Collectors.groupingBy(Object::getClass));
    ClassToListOfTrashMap.show(m);
  }
}
/* Output:
Loading patterns.trash.Cardboard
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Glass
Glass weight: 5.40 * price: 0.23 = 1.24
Glass weight: 4.30 * price: 0.23 = 0.99
Glass weight: 3.60 * price: 0.23 = 0.83
Total Glass value = 3.06
Paper weight: 8.00 * price: 0.10 = 0.80
Paper weight: 6.60 * price: 0.10 = 0.66
Paper weight: 9.10 * price: 0.10 = 0.91
Total Paper value = 2.37
Cardboard weight: 4.40 * price: 0.11 = 0.48
Cardboard weight: 2.20 * price: 0.11 = 0.24
Cardboard weight: 1.20 * price: 0.11 = 0.13
Total Cardboard value = 0.86
Aluminum weight: 1.80 * price: 1.67 = 3.01
Aluminum weight: 3.40 * price: 1.67 = 5.68
Aluminum weight: 2.70 * price: 1.67 = 4.51
Total Aluminum value = 13.19
*/
