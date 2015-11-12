// onjava/TimedAbort.java
// Terminate a program after n seconds.
package onjava;
import java.util.*;

public class TimedAbort {
  public TimedAbort(int n) {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        System.out.println("TimedAbort " + n);
        System.exit(0);
      }
    }, n * 1000);
  }
}
