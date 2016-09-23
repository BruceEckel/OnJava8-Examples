// validating/Time.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

public interface Time {
  static long it(Runnable test) {
    long start = System.nanoTime();
    test.run();
    long delta = System.nanoTime() - start;
    long millis = TimeUnit.NANOSECONDS.toMillis(delta);
    System.out.println(millis);
    return millis;
  }
}
