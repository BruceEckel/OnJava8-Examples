// concurrency/SimplePriorities.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Shows thread priorities.
import java.util.concurrent.*;

public class SimplePriorities implements Runnable {
  private int countDown = 5;
  private volatile double d; // No optimization
  private int priority;
  public SimplePriorities(int priority) {
    this.priority = priority;
  }
  @Override
  public String toString() {
    return Thread.currentThread() + ": " + countDown;
  }
  @Override
  public void run() {
    Thread.currentThread().setPriority(priority);
    while(true) {
      // An expensive, interruptable operation:
      for(int i = 1; i < 100000; i++) {
        d += (Math.PI + Math.E) / (double)i;
        if(i % 1000 == 0)
          Thread.yield();
      }
      System.out.println(this);
      if(--countDown == 0) return;
    }
  }
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++)
      exec.execute(
        new SimplePriorities(Thread.MIN_PRIORITY));
    exec.execute(
        new SimplePriorities(Thread.MAX_PRIORITY));
    exec.shutdown();
  }
}
/* Output: (First 10 Lines)
Thread[pool-1-thread-6,10,main]: 5
Thread[pool-1-thread-1,1,main]: 5
Thread[pool-1-thread-4,1,main]: 5
Thread[pool-1-thread-5,1,main]: 5
Thread[pool-1-thread-3,1,main]: 5
Thread[pool-1-thread-2,1,main]: 5
Thread[pool-1-thread-3,1,main]: 4
Thread[pool-1-thread-5,1,main]: 4
Thread[pool-1-thread-4,1,main]: 4
Thread[pool-1-thread-1,1,main]: 4
                  ...
*/
