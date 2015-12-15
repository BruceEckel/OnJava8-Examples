// concurrency/DaemonFromFactory.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Using a Thread Factory to create daemons.
import java.util.concurrent.*;
import onjava.*;

public class DaemonFromFactory implements Runnable {
  @Override
  public void run() {
    try {
      while(true) {
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(
          Thread.currentThread() + " " + this);
      }
    } catch(InterruptedException e) {
      System.out.println("Interrupted");
    }
  }
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool(
      new DaemonThreadFactory());
    for(int i = 0; i < 10; i++)
      exec.execute(new DaemonFromFactory());
    System.out.println("All daemons started");
    TimeUnit.MILLISECONDS.sleep(500); // Run for a while
  }
}
/* Output: (First 10 Lines)
All daemons started
Thread[Thread-2,5,main] DaemonFromFactory@1bb3d5e
Thread[Thread-8,5,main] DaemonFromFactory@12aef8a
Thread[Thread-9,5,main] DaemonFromFactory@b5ecf4
Thread[Thread-0,5,main] DaemonFromFactory@3e3d06
Thread[Thread-1,5,main] DaemonFromFactory@425a41
Thread[Thread-5,5,main] DaemonFromFactory@4f61c0
Thread[Thread-4,5,main] DaemonFromFactory@53f1ba
Thread[Thread-6,5,main] DaemonFromFactory@baf372
Thread[Thread-3,5,main] DaemonFromFactory@1437b8f
                  ...
*/
