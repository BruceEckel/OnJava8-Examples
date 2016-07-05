// threads/SingleThreadExecutor.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;

public class SingleThreadExecutor {
  public static void main(String[] args) {
    ExecutorService exec =
      Executors.newSingleThreadExecutor();
    for(int id = 0; id < 10; id++)
      exec.execute(new SleepAndPrintTask(id));
    System.out.println("All tasks submitted");
    exec.shutdown();
    while(!exec.isTerminated()) {
      System.out.println(
        Thread.currentThread().getName() +
        " awaiting termination");
      try {
        Thread.sleep(100);
      } catch(InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
/* Output:
All tasks submitted
main awaiting termination
main awaiting termination
SleepAndPrintTask[0] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[1] pool-1-thread-1
SleepAndPrintTask[2] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[3] pool-1-thread-1
main awaiting termination
main awaiting termination
SleepAndPrintTask[4] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[5] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[6] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[7] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[8] pool-1-thread-1
main awaiting termination
SleepAndPrintTask[9] pool-1-thread-1
*/
