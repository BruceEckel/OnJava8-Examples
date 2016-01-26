// onjava/TimeIt.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package onjava;
import java.util.concurrent.*;

public interface TimeIt {
  static long timeIt(Runnable test) {
    long start = System.nanoTime();
    test.run();
    long delta = System.nanoTime() - start;
    long millis = TimeUnit.NANOSECONDS.toMillis(delta);
    System.out.println(millis);
    return millis;
  }
}
