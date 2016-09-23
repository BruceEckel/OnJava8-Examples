// network/tests/TestSimpleServerClient.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package network;
import java.net.*;
import org.junit.jupiter.api.*;

public class TestSimpleServerClient {
  @BeforeAll
  static void startMsg() {
    System.out.println(">>> TestSimpleServerClient");
  }
  @Test
  void basicTest() throws Exception {
    SimpleServer server = new SimpleServer();
    SimpleClient client = new SimpleClient(InetAddress.getLocalHost());
    // Success if no exceptions happen
  }
}
