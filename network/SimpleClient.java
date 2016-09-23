// network/SimpleClient.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Sends lines to the server and
// reads lines the server sends.
package network;
import java.net.*;
import java.io.*;

public class SimpleClient implements Runnable {
  private InetAddress hostAddress;
  public SimpleClient(InetAddress hostAddress) {
    this.hostAddress = hostAddress;
  }
  public void run() {
    System.out.println("hostAddress = " + hostAddress);
    try (
      Socket socket = new Socket(hostAddress, SimpleServer.PORT);
      BufferedReader in =
        new BufferedReader(
          new InputStreamReader(
            socket.getInputStream()));
      PrintWriter out =
        new PrintWriter(
          new BufferedWriter(
            new OutputStreamWriter(
              // Enable auto-flush:
              socket.getOutputStream())), true);
    ) {
      System.out.println("socket: " + socket);
      for(int i = 0; i < 10; i ++) {
        out.println("hello " + i);
        String str = in.readLine();
        System.out.println("client Message : " + str);
      }
      out.println("END");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
