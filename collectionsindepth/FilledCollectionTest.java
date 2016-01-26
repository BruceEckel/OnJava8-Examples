// collectionsindepth/FilledCollectionTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.function.*;
import onjava.*;

class Government implements Supplier<String> {
  String[] foundation = ("strange women lying in ponds " +
    "distributing swords is no basis for a system of " +
    "government").split(" ");
  private int index;
  @Override
  public String get() { return foundation[index++]; }
}

public class FilledCollectionTest {
  public static void main(String[] args) {
    Set<String> set = new LinkedHashSet<>(
      new FilledCollection<>(new Government(), 15));
    // Using the convenience method:
    set.addAll(
      FilledCollection.list(new Government(), 15));
    System.out.println(set);
  }
}
/* Output:
[strange, women, lying, in, ponds, distributing, swords,
is, no, basis, for, a, system, of, government]
*/
