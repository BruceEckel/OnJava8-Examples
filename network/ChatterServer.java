// network/ChatterServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A server that echoes datagrams
import java.net.*;
import java.io.*;

public class ChatterServer implements Runnable {
  static final int INPORT = 1711;
  private byte[] buf = new byte[1000];
  private DatagramPacket dp =
    new DatagramPacket(buf, buf.length);
  @Override
  public void run() {
    // Can listen & send on the same socket:
    try (
      DatagramSocket socket = new DatagramSocket(INPORT)
    ) {
      System.out.println("Server started");
      while(true) {
        // Block until a datagram appears:
        socket.receive(dp);
        String rcvd = Dgram.toString(dp) +
          ", from address: " + dp.getAddress() +
          ", port: " + dp.getPort();
        System.out.println(rcvd);
        String echoString =
          "Echoed: " + rcvd;
        // Extract address and port from received
        // datagram and send it back there:
        DatagramPacket echo =
          Dgram.toDatagram(echoString,
            dp.getAddress(), dp.getPort());
        socket.send(echo);
      }
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
