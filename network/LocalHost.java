//: network/LocalHost.java
// ©2015 MindView LLC: see Copyright.txt
// The local loopback IP address
import java.net.*;

public class LocalHost {
  public static void main(String[] args)
      throws Exception {
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
} ///:~
