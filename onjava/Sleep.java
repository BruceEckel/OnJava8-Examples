// onjava/Sleep.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Sleep for n seconds.
package onjava;
import java.util.concurrent.*;

public class Sleep {
  public Sleep(int n) {
    try {
      TimeUnit.SECONDS.sleep(n);
    } catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
