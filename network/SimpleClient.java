// network/SimpleClient.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Sends to the server, reads from the server
import java.net.*;
import java.io.*;
import java.util.concurrent.atomic.*;

public class SimpleClient implements Runnable {
  public final int port;
  private static AtomicInteger idcount =
    new AtomicInteger(0);
  private final int id = idcount.getAndIncrement();
  private InetAddress host;
  public SimpleClient(InetAddress host, int port) {
    this.host = host;
    this.port = port;
  }
  @Override
  public String toString() {
    return "Client[" + id + "]: ";
  }
  @Override
  public void run() {
    System.out.println(this + "Running");
    try(
      Socket socket = new Socket(host, port);
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
