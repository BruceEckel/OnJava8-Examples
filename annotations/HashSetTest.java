// annotations/HashSetTest.java
// ©2016 MindView LLC: see Copyright.txt
package annotations;
import java.util.*;
import com.mindviewinc.atunit.*;
import onjava.*;

public class HashSetTest {
  HashSet<String> testObject = new HashSet<>();
  @Test void initialization() {
    assert testObject.isEmpty();
  }
  @Test void _contains() {
    testObject.add("one");
    assert testObject.contains("one");
  }
  @Test void _remove() {
    testObject.add("one");
    testObject.remove("one");
    assert testObject.isEmpty();
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command(
      "java com.mindviewinc.atunit.AtUnit HashSetTest");
  }
}
/* Output:
annotations.HashSetTest
  . initialization
  . _remove
  . _contains
OK (3 tests)
*/
