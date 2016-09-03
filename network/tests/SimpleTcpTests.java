// network/tests/SimpleTcpTests.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package network;
import java.util.*;
import java.util.stream.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import onjava.*;

public class SimpleTcpTests {

  @BeforeAll
  static void startMsg() {
    System.out.println(">>> Network Tests <<<");
  }

  @BeforeEach
  void setupServer () { }

  @Test
  void basicTest() throws Exception {
    SimpleServer server = new SimpleServer();
    SimpleClient client = new SimpleClient();
    client.main(null);
    assertTrue(false); // Fail until there are good assertions in the test
  }

  @Test
  void multiTest() throws Exception {
    MultiSimpleClient client = new MultiSimpleClient();
    MultiSimpleServer server = new MultiSimpleServer();
    client.main(null);
    assertTrue(false); // Fail until there are good assertions in the test
  }

  @Test
  void chatterTest() throws Exception {
    ChatterServer server = new ChatterServer();
    ChatterClient.main(null);
    assertTrue(false); // Fail until there are good assertions in the test
  }

}
