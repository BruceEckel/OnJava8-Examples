// network/TestMultiServer.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.net.*;
import java.util.concurrent.*;
import onjava.Nap;

public class TestMultiServer {
  public static void main(String[] args) {
    CompletableFuture.runAsync(new MultiServer());
    for(int i = 0; i < 10; i++) {
      CompletableFuture.runAsync(
        new SimpleClient2(Local.host()));
    }
    new Nap(2);
    // No exceptions mean success
  }
}
