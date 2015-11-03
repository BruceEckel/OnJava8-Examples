// com/mindviewinc/util/Sleep.java
// Sleep for n seconds.
package com.mindviewinc.util;
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
