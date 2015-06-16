//: network/MultiSimpleServer.java
// ©2015 MindView LLC: see Copyright.txt
// A server that uses multithreading
// to handle any number of clients.
// {ValidateByHand}
import java.io.*;
import java.net.*;
import com.mindviewinc.util.*;

class ServeOneSimple extends Thread {
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  public ServeOneSimple(Socket s)
      throws IOException {
    socket = s;
    in =
      new BufferedReader(
        new InputStreamReader(
          socket.getInputStream()));
    // Enable auto-flush:
    out =
      new PrintWriter(
        new BufferedWriter(
          new OutputStreamWriter(
            socket.getOutputStream())), true);
    // If any of the above calls throw an
    // exception, the caller is responsible for
    // closing the socket. Otherwise the thread
    // will close it.
    start(); // Calls run()
  }
  @Override
  public void run() {
    try {
      while (true) {
        String str = in.readLine();
        if(str.equals("END")) break;
        System.out.println("Echoing: " + str);
        out.println(str);
      }
      System.out.println("closing...");
    } catch (IOException e) {
    } finally {
      try {
        socket.close();
      } catch(IOException e) {}
    }
  }
}

public class MultiSimpleServer {
  static final int PORT = 8080;
  public static void main(String[] args)
      throws IOException {
    new TimedAbort(5); // Terminate after 5 seconds
    ServerSocket s = new ServerSocket(PORT);
    System.out.println("Server Started");
    try {
      while(true) {
        // Blocks until a connection occurs:
        Socket socket = s.accept();
        try {
          new ServeOneSimple(socket);
        } catch(IOException e) {
          // If it fails, close the socket,
          // otherwise the thread will close it:
          socket.close();
        }
      }
    } finally {
      s.close();
    }
  }
} ///:~
