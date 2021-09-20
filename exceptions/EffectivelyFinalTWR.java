// exceptions/EffectivelyFinalTWR.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 9
import java.io.*;

public class EffectivelyFinalTWR {
  static void old() {
    try (
      InputStream r1 = new FileInputStream(
        new File("TryWithResources.java"));
      InputStream r2 = new FileInputStream(
        new File("EffectivelyFinalTWR.java"));
    ) {
      r1.read();
      r2.read();
    } catch(IOException e) {
      // Handle exceptions
    }
  }
  static void jdk9() throws IOException {
    final InputStream r1 = new FileInputStream(
      new File("TryWithResources.java"));
    // Effectively final:
    InputStream r2 = new FileInputStream(
      new File("EffectivelyFinalTWR.java"));
    try (r1; r2) {
      r1.read();
      r2.read();
    }
    // r1 and r2 are still in scope. Accessing
    // either one throws an exception:
    r1.read();
    r2.read();
  }
  public static void main(String[] args) {
    old();
    try {
      jdk9();
    } catch(IOException e) {
      System.out.println(e);
    }
  }
}
/* Output:
java.io.IOException: Stream Closed
*/
