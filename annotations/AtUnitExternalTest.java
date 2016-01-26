// annotations/AtUnitExternalTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Creating non-embedded tests
package annotations;
import onjava.atunit.*;
import onjava.*;

public class AtUnitExternalTest extends AtUnitExample1 {
  @Test boolean _methodOne() {
    return methodOne().equals("This is methodOne");
  }
  @Test boolean _methodTwo() { return methodTwo() == 2; }
  public static void
  main(String[] args) throws Exception {
    OSExecute.command("java -cp .. " +
      "onjava.atunit.AtUnit AtUnitExternalTest.class");
  }
}
/* Output:
annotations.AtUnitExternalTest
  . _methodTwo This is methodTwo

  . _methodOne
OK (2 tests)
*/
