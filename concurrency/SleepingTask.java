// concurrency/SleepingTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Calling sleep() to pause for a while.
import java.util.concurrent.*;

public class SleepingTask extends LiftOff {
  @Override
  public void run() {
    try {
      while(countDown-- > 0) {
        System.out.print(status());
        // Old-style:
        // Thread.sleep(100);
        // Modern style:
        TimeUnit.MILLISECONDS.sleep(100);
      }
    } catch(InterruptedException e) {
      System.err.println("Interrupted");
    }
  }
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++)
      exec.execute(new SleepingTask());
    exec.shutdown();
  }
}
/* Output:
#2(9), #4(9), #3(9), #0(9), #1(9), #2(8), #4(8), #3(8),
#0(8), #1(8), #4(7), #3(7), #2(7), #1(7), #0(7), #4(6),
#3(6), #2(6), #0(6), #1(6), #4(5), #2(5), #3(5), #1(5),
#0(5), #4(4), #2(4), #3(4), #1(4), #0(4), #4(3), #2(3),
#3(3), #1(3), #0(3), #4(2), #2(2), #3(2), #0(2), #1(2),
#2(1), #4(1), #3(1), #0(1), #1(1), #2(Liftoff!),
#4(Liftoff!), #3(Liftoff!), #0(Liftoff!), #1(Liftoff!),
*/
