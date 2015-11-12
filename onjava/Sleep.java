// onjava/Sleep.java
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
