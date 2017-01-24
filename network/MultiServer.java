// network/MultiServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Uses concurrency to handle any number of clients.
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class MultiServer implements Runnable {
  private final int port;
  public MultiServer(int port) {
    this.port = port;
  }
  @Override
  public void run() {
    System.out.println("Server: Running");
    try (
      ServerSocket ss = new ServerSocket(port)
    ) {
      System.out.println("Server: " + ss);
      for(int i = 0; i < 10; i++)
        CompletableFuture
          .runAsync(new SimpleServer(ss));
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
