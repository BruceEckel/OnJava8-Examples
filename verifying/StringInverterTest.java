// verifying/StringInverterTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ValidateByHand} // Don't run by itself
import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
// import org.junit.platform.runner.JUnitPlatform;

public class StringInverterTest {
  static StringInverter inverter;
  @Test
  public final void basicInversion_Succeed() {
    String in = "Exit, Pursued by a Bear.";
    String out = "eXIT, pURSUED BY A bEAR.";
    assertEquals(inverter.invert(in), out);
  }
  @Test
  public final void basicInversion_Fail() {
    expectThrows(RuntimeException.class, () -> {
      assertEquals(inverter.invert("X"), "X");
    });
  }
  @Test
  public final void allowedCharacters_Fail() {
    expectThrows(RuntimeException.class, () -> {
      inverter.invert(";-_()*&^%$#@!~`");
      inverter.invert("0123456789");
    });
  }
  @Test
  public final void allowedCharacters_Succeed() {
    inverter.invert("abcdefghijklmnopqrstuvwxyz ,.");
    inverter.invert("ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.");
  }
  @Test
  public final void lengthLessThan26_Fail() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() > 25);
    expectThrows(RuntimeException.class, () -> {
      inverter.invert(str);
    });
  }
  @Test
  public final void lengthLessThan26_Succeed() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() < 26);
    inverter.invert(str);
  }
  /*
  public static void main(String[] args) throws Exception{
    assertEquals(args.length, 1);
    inverter = (StringInverter)
      Class.forName(args[0]).newInstance();
    Result result = org.junit.runner.JUnitCore.runClasses(
      StringInverterTest.class);
    List<Failure> failures = result.getFailures();
    System.out.printf("%s has %d FAILURES:\n",
      args[0], failures.size());
    int count = 1;
    for(Failure f : failures) {
      System.out.printf("Failure %d:\n", count++);
      System.out.println(f.getDescription());
      System.out.println(f.getMessage());
    }
  }
  */
}
