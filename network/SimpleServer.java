// network/SimpleServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Echoes what the client sends.
import java.io.*;
import java.net.*;

public class SimpleServer implements Runnable {
  @Override
  public String toString() { return "Server: "; }
  @Override
  public void run() {
    try (
      ServerSocket s =
        new ServerSocket(SimpleClient.PORT);
      // Blocks until a connection occurs:
      Socket socket = s.accept();
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            socket.getInputStream()));
      PrintWriter out =
        new PrintWriter(
          new BufferedWriter(
            new OutputStreamWriter(
              // Enable auto-flush:
              socket.getOutputStream())), true)
    ) {
      System.out.println(this.toString() + socket);
      in.lines().anyMatch(message -> {
        if(message.equals("END")) {
          System.out.println(this +
            "Received END. Closing Socket.");
          return true;
        }
        out.println(this + message);
        return false;
      });
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
