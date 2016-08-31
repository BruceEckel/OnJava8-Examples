// network/ChatterClient.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ValidateByHand}
// Tests the ChatterServer by starting multiple
// clients, each of which sends datagrams.
import java.net.*;
import java.io.*;
import onjava.*;

public class ChatterClient extends Thread {
  // Can listen & send on the same socket:
  private DatagramSocket s;
  private InetAddress hostAddress;
  private byte[] buf = new byte[1000];
  private DatagramPacket dp =
    new DatagramPacket(buf, buf.length);
  private int id;

  public ChatterClient(int identifier) {
    id = identifier;
    try {
      // Auto-assign port number:
      s = new DatagramSocket();
      hostAddress =
        InetAddress.getByName("localhost");
    } catch(UnknownHostException e) {
      System.err.println("Cannot find host");
      System.exit(1);
    } catch(SocketException e) {
      System.err.println("Can't open socket");
      throw new RuntimeException(e);
    }
    System.out.println("ChatterClient starting");
  }
  public void sendAndEcho(String msg) {
    try {
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
    for(int i = 0; i <= 25; i++)
      sendAndEcho("Client #" + id + ", message #" + i);
  }
  public static void main(String[] args) {
    new TimedAbort(5); // Terminate after 5 seconds
    for(int i = 0; i <= 10; i++)
      new ChatterClient(i).start();
  }
}
