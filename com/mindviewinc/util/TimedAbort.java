//: com/mindviewinc/util/TimedAbort.java
// ©2015 MindView LLC: see Copyright.txt
// Terminate a program after n seconds.
package com.mindviewinc.util;
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
} ///:~
