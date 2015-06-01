//: network/WhoAmI.java
// ©2015 MindView LLC: see Copyright.txt
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
} ///:~
