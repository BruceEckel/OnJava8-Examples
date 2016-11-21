// annotations/StackLStringTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Applying @Unit to generics
// {java onjava.atunit.AtUnit
// build/classes/main/annotations/StackLStringTest.class}
package annotations;
import onjava.atunit.*;
import onjava.*;

public class StackLStringTest extends StackL<String> {
  @Test void tPush() {
    push("one");
    assert top().equals("one");
    push("two");
    assert top().equals("two");
  }
  @Test void tPop() {
    push("one");
    push("two");
    assert pop().equals("two");
    assert pop().equals("one");
  }
  @Test void tTop() {
    push("A");
    push("B");
    assert top().equals("B");
    assert top().equals("B");
  }
}
/* Output:
annotations.StackLStringTest
  . tPop
  . tTop
  . tPush
OK (3 tests)
*/
