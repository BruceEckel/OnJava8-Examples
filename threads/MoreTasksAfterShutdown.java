// threads/MoreTasksAfterShutdown.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;

public class MoreTasksAfterShutdown {
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService exec =
      Executors.newSingleThreadExecutor();
    exec.execute(new SleepAndPrintTask(1));
    exec.shutdown();
    try {
      exec.execute(new SleepAndPrintTask(99));
    } catch(RejectedExecutionException e) {
      System.out.println(e);
    }
    exec.awaitTermination(5, TimeUnit.SECONDS);
  }
}
/* Output:
java.util.concurrent.RejectedExecutionException: Task
SleepAndPrintTask[99] rejected from
java.util.concurrent.ThreadPoolExecutor@25154f[Shutting
down, pool size = 1, active threads = 1, queued tasks = 0,
completed tasks = 0]
SleepAndPrintTask[1] pool-1-thread-1
*/
