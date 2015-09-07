// com/mindviewinc/util/DaemonThreadPoolExecutor.java
// ©2015 MindView LLC: see Copyright.txt
package com.mindviewinc.util;
import java.util.concurrent.*;

public class DaemonThreadPoolExecutor
extends ThreadPoolExecutor {
  public DaemonThreadPoolExecutor() {
    super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS,
      new SynchronousQueue<>(),
      new DaemonThreadFactory());
  }
}
