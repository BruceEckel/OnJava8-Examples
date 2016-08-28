// verifying/StringInverterTests.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StringInverterTests {
  StringInverter inverter = new StringInverter4();
  @Test
  void basicInversion_Succeed() {
    String in = "Exit, Pursued by a Bear.";
    String out = "eXIT, pURSUED BY A bEAR.";
    assertEquals(inverter.invert(in), out);
  }
  @Test
  void basicInversion_Fail() {
    expectThrows(Error.class, () -> {
      assertEquals(inverter.invert("X"), "X");
    });
  }
  @Test
  void allowedCharacters_Fail() {
    expectThrows(RuntimeException.class, () -> {
      inverter.invert(";-_()*&^%$#@!~`");
      inverter.invert("0123456789");
    });
  }
  @Test
  void allowedCharacters_Succeed() {
    String lowcase = "abcdefghijklmnopqrstuvwxyz ,.";
    String upcase =  "ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.";
    assertEquals(inverter.invert(lowcase), upcase);
    assertEquals(inverter.invert(upcase), lowcase);
  }
  @Test
  void lengthLessThan31_Fail() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() > 30);
    expectThrows(RuntimeException.class, () -> {
      inverter.invert(str);
    });
  }
  @Test
  void lengthLessThan31_Succeed() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() < 31);
    inverter.invert(str);
  }
}
