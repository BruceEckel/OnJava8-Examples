// network/MultiServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Uses concurrency to handle any number of clients.
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

class Serve1 implements Runnable {
  private ServerSocket ss;
  public Serve1(ServerSocket ss) {
    this.ss = ss;
  }
  @Override
  public String toString() { return "Serve1: "; }
  @Override
  public void run() {
    System.out.println(this + "Running");
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
          System.out.println(this +
            "Received END. Closing Socket.");
          return true;
        }
        System.out.println(
          this + "Message: " + message);
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
    System.out.println("Server: Running");
    try (
      ServerSocket ss =
        new ServerSocket(SimpleClient.PORT)
    ) {
      System.out.println("Server: " + ss);
      for(int i = 0; i < 10; i++)
        CompletableFuture
          .runAsync(new Serve1(ss));
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
