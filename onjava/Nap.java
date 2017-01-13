// onjava/Nap.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Sleep for n milliseconds
package onjava;
import java.util.concurrent.*;

public class Nap {
  public Nap(int n) {
    try {
      TimeUnit.MILLISECONDS.sleep(n);
    } catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  public Nap(int n, String msg) {
    this(n);
    System.out.println(msg);
  }
}
