// annotations/AtUnitExternalTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Creating non-embedded tests
// {java onjava.atunit.AtUnit
// build/classes/main/annotations/AtUnitExternalTest.class}
package annotations;
import onjava.atunit.*;
import onjava.*;

public class AtUnitExternalTest extends AtUnitExample1 {
  @Test boolean _methodOne() {
    return methodOne().equals("This is methodOne");
  }
  @Test boolean _methodTwo() { return methodTwo() == 2; }
}
/* Output:
annotations.AtUnitExternalTest
  . _methodTwo This is methodTwo

  . _methodOne
OK (2 tests)
*/
