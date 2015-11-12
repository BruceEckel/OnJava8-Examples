// annotations/AtUnitComposition.java
// Creating non-embedded tests.
package annotations;
import com.mindviewinc.atunit.*;
import onjava.*;

public class AtUnitComposition {
  AtUnitExample1 testObject = new AtUnitExample1();
  @Test boolean _methodOne() {
    return
      testObject.methodOne().equals("This is methodOne");
  }
  @Test boolean _methodTwo() {
    return testObject.methodTwo() == 2;
  }
  public static void main(String[] args) throws Exception {
    OSExecute.command(
     "java com.mindviewinc.atunit.AtUnit AtUnitComposition");
  }
}
/* Output:
annotations.AtUnitComposition
  . _methodOne
  . _methodTwo This is methodTwo
OK (2 tests)
*/
