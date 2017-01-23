// network/Local.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.net.*;

public class Local {
  public static InetAddress host() {
    try {
      return InetAddress.getByName(null);
    } catch(UnknownHostException e) {
      throw new RuntimeException(e);
    }
  }
}
