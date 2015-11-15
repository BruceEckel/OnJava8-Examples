// containersindepth/CollectionDataTest.java
// ©2016 MindView LLC: see Copyright.txt
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

public class CollectionDataTest {
  public static void main(String[] args) {
    Set<String> set = new LinkedHashSet<>(
      new CollectionData<>(new Government(), 15));
    // Using the convenience method:
    set.addAll(CollectionData.list(new Government(), 15));
    System.out.println(set);
  }
}
/* Output:
[strange, women, lying, in, ponds, distributing, swords,
is, no, basis, for, a, system, of, government]
*/
