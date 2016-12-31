// network/tests/ChatterTest.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package network;
import java.net.*;
import org.junit.jupiter.api.*;

public class ChatterTest {
  @BeforeAll
  static void startMsg() {
    System.out.println(">>> ChatterTest");
  }
  @Test
  void chatterTest() throws Exception {
    // <* These must be handed to an executor: *>
    new ChatterServer();
    new ChatterClient(InetAddress.getLocalHost());
    // No exceptions means success
  }
}
