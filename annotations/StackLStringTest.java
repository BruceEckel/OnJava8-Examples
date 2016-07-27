// annotations/StackLStringTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Applying @Unit to generics
// {main: onjava.atunit.AtUnit}
// {Args: build/classes/main/annotations/StackLStringTest.class}
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
}
/* Output:
annotations.StackLStringTest
  . _pop
  . _top
  . _push
OK (3 tests)
*/
