// network/TestSimpleServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import onjava.Nap;

public class TestSimpleServer {
  // Choose a port outside of the range 1-1024:
  public static final int port = 8080;
  public static void main(String[] args) {
    try (
      ServerSocket ss =
        new ServerSocket(port)
    ) {
      CompletableFuture.runAsync(
        new SimpleServer(ss));
      CompletableFuture.runAsync(
        new SimpleClient(Local.host(), port));
      new Nap(1);
      // Success if no exceptions happen
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}
