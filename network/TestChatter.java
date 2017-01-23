// network/TestChatter.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.net.*;
import onjava.Nap;

public class TestChatter {
  public static void main(String[] args) {
    CompletableFuture.runAsync(
      new ChatterServer());
    CompletableFuture.runAsync(
      new ChatterClient(Local.host()));
    new Nap(1);
    // No exceptions means success
  }
}
