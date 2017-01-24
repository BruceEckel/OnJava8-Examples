// network/TestMultiServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.net.*;
import java.util.concurrent.*;
import onjava.Nap;

public class TestMultiServer {
  public static final int port = 8080;
  public static void main(String[] args) {
    CompletableFuture.runAsync(
      new MultiServer(port));
    new Nap(1); // Let the server get started
    for(int i = 0; i < 10; i++) {
      CompletableFuture.runAsync(
        new SimpleClient(Local.host(), port));
    }
    new Nap(4);
    // No exceptions mean success
  }
}
