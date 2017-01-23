// network/SimpleClient2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.net.*;
import java.io.*;

public class SimpleClient2 implements Runnable {
  private InetAddress address;
  private static int counter = 0;
  private int id = counter++;
  public SimpleClient2(InetAddress address) {
    System.out.println("Making client " + id);
    this.address = address;
  }
  @Override
  public void run() {
    System.out.println("Running client " + id);
    try (
      Socket socket =
        new Socket(address, ServeOne.PORT);
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
      for(int i = 0; i < 25; i++) {
        out.println("Client " + id + ": " + i);
        String str = in.readLine();
        System.out.println(str);
      }
      out.println("END");
    } catch(IOException ex) {
        throw new RuntimeException(ex);
    }
  }
}
