// network/WhoAmI.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Finds out your machine name and network address
// when you're connected to the Internet.
import java.net.*;

public class WhoAmI {
  public static void main(String[] args)
      throws Exception {
    InetAddress whoami = InetAddress.getLocalHost();
    System.out.println(whoami.getHostName());
    System.out.println(whoami.getHostAddress());
  }
}
/* Output:
groot
192.168.70.108
*/
