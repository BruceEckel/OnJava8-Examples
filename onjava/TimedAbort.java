// onjava/TimedAbort.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Terminate a program after n seconds
package onjava;
import java.util.concurrent.*;

public class TimedAbort {
  public TimedAbort(int n) {
    CompletableFuture.runAsync(() -> {
      new Nap(1000 * n);
      System.out.println("TimedAbort " + n);
      System.exit(0);
    });
  }
}
