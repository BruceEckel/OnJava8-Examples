//: net/mindview/util/DaemonThreadFactory.java
// ©2015 MindView LLC: see Copyright.txt
package net.mindview.util;
import java.util.concurrent.*;

public class DaemonThreadFactory implements ThreadFactory {
  @Override
  public Thread newThread(Runnable r) {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  }
} ///:~
