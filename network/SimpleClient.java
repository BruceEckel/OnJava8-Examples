// network/SimpleClient.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Sends to the server, reads from the server
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.*;

public class SimpleClient implements Runnable {
  // Choose a port outside of the range 1-1024:
  public static final int PORT = 8080;
  private static AtomicInteger idcount =
    new AtomicInteger(0);
  private final int id = idcount.getAndIncrement();
  private InetAddress hostAddress;
  public SimpleClient(InetAddress hostAddress) {
    this.hostAddress = hostAddress;
  }
  @Override
  public String toString() {
    return "Client[" + id + "]: ";
  }
  @Override
  public void run() {
    System.out.println(this + "Running");
    try(
      Socket socket = new Socket(hostAddress, PORT);
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            socket.getInputStream()));
      PrintWriter out =
        new PrintWriter(
          new BufferedWriter(
            new OutputStreamWriter(
              // Enable auto-flush:
              socket.getOutputStream())), true);
    ) {
      System.out.println(this.toString() + socket);
      for(int i = 0; i < 10; i ++) {
        out.println(this + "(*" + i + "*)");
        System.out.println(this +
          "Received '" + in.readLine() + "'");
      }
      out.println("END");
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
