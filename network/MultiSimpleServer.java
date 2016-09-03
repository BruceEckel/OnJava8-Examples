// network/MultiSimpleServer.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Uses threads to handle any number of clients.
// {ValidateByHand}
import java.io.*;
import java.net.*;
import onjava.*;

class ServeOneSimple implements Runnable {
  private ServerSocket ss;
  public
  ServeOneSimple(ServerSocket ss) throws IOException {
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
              // Enable auto-flush:
              socket.getOutputStream())), true)
    ) {
      while (true) {
        String str = in.readLine();
        if(str.equals("END")) break;
        System.out.println("Echoing: " + str);
        out.println(str);
      }
      System.out.println("closing socket...");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

public class MultiSimpleServer {
  static final int PORT = 8080;
  public static void
  main(String[] args) throws IOException {
    new TimedAbort(5); // Terminate after 5 seconds
    System.out.println("Server Started");
    try (ServerSocket ss = new ServerSocket(PORT)){
      // Block until a connection occurs:
      while(true) {
          new ServeOneSimple(ss);
      }
    }
  }
}
