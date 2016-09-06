// network/SimpleServer.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Echoes what the client sends.
package network;
import java.io.*;
import java.net.*;

public class SimpleServer implements Runnable {
  // Choose a port outside of the range 1-1024:
  public static final int PORT = 8080;

  public void run() {
    try (
      ServerSocket s = new ServerSocket(PORT);
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
      System.out.println("Connection: " + socket);
      in.lines().anyMatch(message->{
        if (message.equals("END")) {
          System.out.println("Received END. Closing Socket.");
          return true;
        }
        System.out.println("Server Response: " + message);
        out.println(message);
        return false;
      });
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
