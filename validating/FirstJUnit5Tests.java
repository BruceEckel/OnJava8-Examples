// validating/FirstJUnit5Tests.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package validating;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class FirstJUnit5Tests {
  @Test
  void myFirstTest() {
    System.out.println("FirstJUnit5Tests");
    assertEquals(2, 1 + 1);
  }
}
