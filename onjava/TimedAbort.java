// onjava/TimedAbort.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Terminate a program after n seconds
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
