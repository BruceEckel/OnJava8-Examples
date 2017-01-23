// onjava/Nap.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package onjava;
import java.util.concurrent.*;

public class Nap {
  // Seconds:
  public Nap(int n) {
    try {
      TimeUnit.SECONDS.sleep(n);
    } catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  // Fractions of a second:
  public Nap(double d) {
    try {
      TimeUnit.MILLISECONDS.sleep((int)(1000 * d));
    } catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public Nap(int n, String msg) {
    this(n);
    System.out.println(msg);
  }
  public Nap(double d, String msg) {
    this(d);
    System.out.println(msg);
  }
}
