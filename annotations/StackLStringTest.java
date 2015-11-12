// annotations/StackLStringTest.java
// Applying @Unit to generics.
package annotations;
import com.mindviewinc.atunit.*;
import onjava.*;

public class StackLStringTest extends StackL<String> {
  @Test void _push() {
    push("one");
    assert top().equals("one");
    push("two");
    assert top().equals("two");
  }
  @Test void _pop() {
    push("one");
    push("two");
    assert pop().equals("two");
    assert pop().equals("one");
  }
  @Test void _top() {
    push("A");
    push("B");
    assert top().equals("B");
    assert top().equals("B");
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command(
      "java com.mindviewinc.atunit.AtUnit StackLStringTest");
  }
}
/* Output:
annotations.StackLStringTest
  . _push
  . _top
  . _pop
OK (3 tests)
*/
