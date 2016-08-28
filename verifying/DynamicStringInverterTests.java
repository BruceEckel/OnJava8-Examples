// verifying/DynamicStringInverterTests.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.*;

class DynamicStringInverterTests {
  // Combine operations to prevent code duplication:
  Stream<DynamicTest>
  testVersions(String id, Consumer<StringInverter> test) {
    List<StringInverter> versions = Arrays.asList(
      new StringInverter1(), new StringInverter2(),
      new StringInverter3(), new StringInverter4());
    return DynamicTest.stream(versions.iterator(),
      (version) -> version.getClass().getSimpleName(),
      (stringInverter) -> {
        System.out.println(
          stringInverter.getClass().getSimpleName() +
          ": " + id
        );
        try { // Capture failing tests
          test.accept(stringInverter);
        } catch(Error | Exception e) {
          System.out.println(e.getMessage());
        }
      }
    );
  }
  void isTrue(String description, boolean assertion) {
    System.out.print(description + ": ");
    System.out.println(assertion);
  }
  void isEqual(String lval, String rval) {
    System.out.print(lval + " equals " + rval);
    if(!lval.equals(rval))
      System.out.println(" FAIL");
    else
      System.out.println();
  }
  @BeforeAll
  static void startMsg() {
    System.out.println(
      ">>> Starting DynamicStringInverterTests <<<");
  }
  @AfterAll
  static void endMsg() {
    System.out.println(
      ">>> Finished DynamicStringInverterTests <<<");
  }
  @TestFactory
  Stream<DynamicTest> basicInversion_Succeed() {
    return testVersions(
      "A Basic Inversion that Succeeds",
      (version) -> {
        String in = "Exit, Pursued by a Bear.";
        String out = "eXIT, pURSUED BY A bEAR.";
        isEqual(version.invert(in), out);
      }
    );
  }
  @TestFactory
  Stream<DynamicTest> basicInversion_Fail() {
    return testVersions(
      "A Basic Inversion that Fails",
      (version) -> isEqual(version.invert("X"), "X"));
  }
  @TestFactory
  Stream<DynamicTest> allowedCharacters_Fail() {
    return testVersions(
      "Disallowed characters (throws exception)",
      (version) -> {
        try {
          version.invert(";-_()*&^%$#@!~`");
          version.invert("0123456789");
          System.out.println("Success");
        } catch(Exception e) {
          System.out.println("FAIL: " + e.getMessage());
        }
      }
    );
  }
  @TestFactory
  Stream<DynamicTest> allowedCharacters_Succeed() {
    return testVersions(
      "Allowed Characters (Succeeds)",
      (version) -> {
        version.invert("abcdefghijklmnopqrstuvwxyz ,.");
        version.invert("ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.");
      }
    );
  }
  @TestFactory
  Stream<DynamicTest> lengthLessThan31_Fail() {
    return testVersions(
      "Length must be less than 31 (Throws Exception)",
      (version) -> {
        String str = "xxxxxxxxxxxxxxxxxxxxxxxxxx";
        assertTrue(str.length() > 30);
        try {
          version.invert(str);
          System.out.println("Success");
        } catch(Exception e) {
          System.out.println("FAIL: " + e.getMessage());
        }
      }
    );
  }
  @TestFactory
  Stream<DynamicTest> lengthLessThan31_Succeed() {
    String str = "xxxxxxxxxxxxxxxxxxxxxxxxx";
    assertTrue(str.length() < 31);
    return testVersions(
      "Length must be less than 31 (Succeeds)",
      (version) -> {
        version.invert(str);
        System.out.println("Success");
      }
    );
  }
}
