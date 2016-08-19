// verifying/StringInverterTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ValidateByHand} // Don't run by itself
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.*;
import org.junit.runner.notification.Failure;

public class StringInverterTest {
  static StringInverter inverter;
  @Test
  public final void basicInversion_Succeed() {
    String in = "Exit, Pursued by a Bear.";
    String out = "eXIT, pURSUED BY A bEAR.";
    assertEquals(inverter.invert(in), out);
  }
  @Test(expected = ComparisonFailure.class)
  public final void basicInversion_Fail() {
    assertEquals(inverter.invert("X"), "X");
  }
  @Test(expected = RuntimeException.class)
  public final void allowedCharacters_Fail() {
    inverter.invert(";-_()*&^%$#@!~`");
    inverter.invert("0123456789");
  }
  /*@Test
  public final void allowedCharacters_Succeed() {
    inverter.invert("abcdefghijklmnopqrstuvwxyz ,.");
    inverter.invert("ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.");
    assertTrue(true); // No exception thrown
  }*/
  @Test(expected = RuntimeException.class)
  public final void lengthLessThan26_Fail() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() > 25);
    inverter.invert(str);
  }
  @Test
  public final void lengthLessThan26_Succeed() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() < 26);
    inverter.invert(str);
    assertTrue(true); // No exception thrown
  }
  public static void main(String[] args) throws Exception{
    assertEquals(args.length, 1);
    inverter = (StringInverter)
      Class.forName(args[0]).newInstance();
    Result result = JUnitCore.runClasses(
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
}
