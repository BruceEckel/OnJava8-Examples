// network/tests/MultiTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package network;
import java.net.*;
import org.junit.jupiter.api.*;

public class MultiTest {
  @BeforeAll
  static void startMsg() {
    System.out.println(">>> MultiClientTest");
  }
  @Test
  void multiTest() throws Exception {
    final int MAX_THREADS = 40;
    new MultiServer();
    InetAddress address = InetAddress.getByName(null);
    while(true) {
      if(SimpleClient2.threadCount() < MAX_THREADS)
        new SimpleClient2(address);
      Thread.sleep(100);
    }
    // No exceptions mean success
  }
}
