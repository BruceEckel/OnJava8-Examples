// onjava/DaemonThreadFactory.java
// ©2016 MindView LLC: see Copyright.txt
package onjava;
import java.util.concurrent.*;

public class DaemonThreadFactory implements ThreadFactory {
  @Override
  public Thread newThread(Runnable r) {
    Thread t = new Thread(r);
    t.setDaemon(true);
    return t;
  }
}
