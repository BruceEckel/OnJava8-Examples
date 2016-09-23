// network/WhoAmI.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Discovers your machine name and network address.
import java.net.*;

public class WhoAmI {
  public static void
  main(String[] args) throws Exception {
    InetAddress whoami = InetAddress.getLocalHost();
    System.out.println(whoami.getHostName());
    System.out.println(whoami.getHostAddress());
  }
}
/* Output:
MindviewToshibaLaptop
192.168.0.104
*/
