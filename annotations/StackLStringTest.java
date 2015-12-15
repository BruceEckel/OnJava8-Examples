// annotations/StackLStringTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Applying @Unit to generics.
package annotations;
import onjava.atunit.*;
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
      "java -cp .. onjava.atunit.AtUnit StackLStringTest.class");
  }
}
/* Output:
annotations.StackLStringTest
  . _top
  . _push
  . _pop
OK (3 tests)
*/
