// network/TestSimpleServerClient.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.net.*;
import java.util.concurrent.*;
import onjava.Nap;

public class TestSimpleServerClient {
  public static void main(String[] args) {
    CompletableFuture.runAsync(
      new SimpleServer());
    CompletableFuture.runAsync(
      new SimpleClient(Local.host()));
    new Nap(1);
    // Success if no exceptions happen
  }
}
