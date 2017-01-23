// network/MultiServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Uses concurrency to handle any number of clients.
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class ServeOne implements Runnable {
  static final int PORT = 8080;
  private ServerSocket ss;
  public ServeOne(ServerSocket ss) {
    this.ss = ss;
  }
  @Override
  public void run() {
    System.out.println("Starting ServeOne");
    try (
      Socket socket = ss.accept();
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            socket.getInputStream()));
      PrintWriter out =
        new PrintWriter(
          new BufferedWriter(
            new OutputStreamWriter(
              // Boolean enables auto-flush
              socket.getOutputStream())), true)
    ) {
      in.lines().anyMatch(message -> {
        if(message.equals("END")) {
          System.out.println(
            "Received END. Closing Socket.");
          return true;
        }
        System.out.println(
          "Message  : " + message);
        out.println(message);
        return false;
      });
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}

public class MultiServer implements Runnable {
  @Override
  public void run() {
    System.out.println("Running MultiServer");
    try (
      ServerSocket ss =
        new ServerSocket(ServeOne.PORT)
    ) {
      while(true)
        CompletableFuture.runAsync(new ServeOne(ss));
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
