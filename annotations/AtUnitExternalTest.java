// annotations/AtUnitExternalTest.java
// Creating non-embedded tests.
package annotations;
import com.mindviewinc.atunit.*;
import com.mindviewinc.util.*;

public class AtUnitExternalTest extends AtUnitExample1 {
  @Test boolean _methodOne() {
    return methodOne().equals("This is methodOne");
  }
  @Test boolean _methodTwo() { return methodTwo() == 2; }
  public static void main(String[] args) throws Exception {
    OSExecute.command(
     "java com.mindviewinc.atunit.AtUnit AtUnitExternalTest");
  }
}
/* Output:
annotations.AtUnitExternalTest
  . _methodTwo This is methodTwo
  . _methodOne
OK (2 tests)
*/
