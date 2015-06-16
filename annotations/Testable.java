//: annotations/Testable.java
// ©2015 MindView LLC: see Copyright.txt
package annotations;
import com.mindviewinc.atunit.*;

public class Testable {
  public void execute() {
    System.out.println("Executing..");
  }
  @Test void testExecute() { execute(); }
} ///:~
