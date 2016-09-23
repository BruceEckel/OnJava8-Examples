// network/ChatterClient.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Starts multiple clients, each of which sends datagrams.
package network;
import java.net.*;
import java.io.*;

public class ChatterClient implements Runnable {
  private InetAddress hostAddress;
  private byte[] buf = new byte[1000];
  private DatagramPacket dp =
    new DatagramPacket(buf, buf.length);
  private static int counter = 0;
  private int id = counter++;

  public ChatterClient(InetAddress hostAddress) {
    this.hostAddress = hostAddress;
    System.out.println("ChatterClient #" + id + " starting");
  }
  public void sendAndEcho(String msg) {
    try (
      // Auto-assign port number:
      DatagramSocket s = new DatagramSocket();
    ) {
      // Make and send a datagram:
      s.send(Dgram.toDatagram(
        msg, hostAddress, ChatterServer.INPORT));
      // Block until it echoes back:
      s.receive(dp);
      // Display the echoed contents:
      String rcvd = "Client #" + id +
        ", rcvd from " +
        dp.getAddress() + ", " +
        dp.getPort() + ": " +
        Dgram.toString(dp);
      System.out.println(rcvd);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public void run() {
    for(int i = 0; i <= 5; i++)
      sendAndEcho("Client #" + id + ", message #" + i);
  }
}
