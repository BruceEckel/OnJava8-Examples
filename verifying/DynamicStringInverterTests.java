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
  Stream<DynamicTest>
  multiple_version_test(Consumer<StringInverter> test) {
    List<StringInverter> versions = Arrays.asList(
      new StringInverter1(), new StringInverter2(),
      new StringInverter3(), new StringInverter4());
    return DynamicTest.stream(versions.iterator(),
      (version) -> version.getClass().getSimpleName(),
      test
    );
  }

  @TestFactory
  Stream<DynamicTest> basicInversion_Succeed() {
    return multiple_version_test( (version) -> {
      String in = "Exit, Pursued by a Bear.";
      String out = "eXIT, pURSUED BY A bEAR.";
      assertEquals(version.invert(in), out);
    });
  }

  @TestFactory
  Stream<DynamicTest> basicInversion_Fail() {
    return multiple_version_test( (version) -> {
      expectThrows(RuntimeException.class, () -> {
        assertEquals(version.invert("X"), "X");
      });
    });
  }

  @TestFactory
  Stream<DynamicTest> allowedCharacters_Fail() {
    return multiple_version_test( (version) -> {
      expectThrows(RuntimeException.class, () -> {
        version.invert(";-_()*&^%$#@!~`");
        version.invert("0123456789");
      });
    });
  }

  @TestFactory
  Stream<DynamicTest> allowedCharacters_Succeed() {
    return multiple_version_test( (version) -> {
      version.invert("abcdefghijklmnopqrstuvwxyz ,.");
      version.invert("ABCDEFGHIJKLMNOPQRSTUVWXYZ ,.");
    });
  }

  @TestFactory
  Stream<DynamicTest> lengthLessThan26_Fail() {
    return multiple_version_test( (version) -> {
      String str = "xxxxxxxxxxxxxxxxxxxxxxxxxx";
      assertTrue(str.length() > 25);
      expectThrows(RuntimeException.class, () -> {
        version.invert(str);
      });
    });
  }

  @TestFactory
  Stream<DynamicTest> lengthLessThan26_Succeed() {
    return multiple_version_test( (version) -> {
      String str = "xxxxxxxxxxxxxxxxxxxxxxxxx";
      assertTrue(str.length() < 26);
      version.invert(str);
    });
  }
}
