// network/LocalHost.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// The local loopback IP address
import java.net.*;

public class LocalHost {
  public static void
  main(String[] args) throws Exception {
    InetAddress
      local1 = InetAddress.getByName(null),
      local2 = InetAddress.getByName("localhost"),
      local3 = InetAddress.getByName("127.0.0.1"),
      local4 = InetAddress.getLoopbackAddress();
    System.out.println(local1);
    System.out.println(local2);
    System.out.println(local3);
    System.out.println(local4);
  }
}
/* Output:
localhost/127.0.0.1
localhost/127.0.0.1
/127.0.0.1
localhost/127.0.0.1
*/
