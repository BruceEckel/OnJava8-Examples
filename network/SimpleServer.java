// network/SimpleServer.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Echoes what the client sends.
// {ValidateByHand}
package network;
import java.io.*;
import java.net.*;

public class SimpleServer {
  // Choose a port outside of the range 1-1024:
  public static final int PORT = 8080;
  public static void
  main(String[] args) throws IOException {
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
      while (true) {
        String str = in.readLine();
        if(str.equals("END")) break;
        System.out.println("Echoing: " + str);
        out.println(str);
      }
    }
  }
}
