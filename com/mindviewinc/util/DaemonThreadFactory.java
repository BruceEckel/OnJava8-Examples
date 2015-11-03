// com/mindviewinc/util/DaemonThreadFactory.java
package com.mindviewinc.util;
import java.util.concurrent.*;

public class DaemonThreadFactory implements ThreadFactory {
  @Override
  public Thread newThread(Runnable r) {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  }
}
