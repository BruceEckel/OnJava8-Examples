// annotations/Testable.java
package annotations;
import com.mindviewinc.atunit.*;

public class Testable {
  public void execute() {
    System.out.println("Executing..");
  }
  @Test void testExecute() { execute(); }
}
