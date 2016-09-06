// network/MultiServer.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Uses threads to handle any number of clients.
package network;
import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServeOne implements Runnable {
  private ServerSocket ss;
  static final int PORT = 8080;
  public ServeOne(ServerSocket ss) throws IOException {
    this.ss = ss;
  }
  @Override
  public void run() {
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
        if (message.equals("END")) {
          System.out.println("Received END. Closing Socket.");
          return true;
        }
        System.out.println("Message  : " + message);
        out.println(message);
        return false;
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

public class MultiServer implements Runnable {
  private ExecutorService pool;
  public void run() {
    pool = Executors.newFixedThreadPool(30);
    try (ServerSocket ss = new ServerSocket(ServeOne.PORT)) {
      while (true) {
        pool.submit(new ServeOne(ss));
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
