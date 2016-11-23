// concurrent/CachedThreadPool.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

public class CachedThreadPool {
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    for(int id = 0; id < 10; id++)
      exec.execute(new SleepAndPrintTask(id));
    exec.shutdown();
    exec.awaitTermination(5, TimeUnit.SECONDS);
  }
}
/* Output:
SleepAndPrintTask[2] pool-1-thread-3
SleepAndPrintTask[9] pool-1-thread-10
SleepAndPrintTask[6] pool-1-thread-7
SleepAndPrintTask[5] pool-1-thread-6
SleepAndPrintTask[7] pool-1-thread-8
SleepAndPrintTask[8] pool-1-thread-9
SleepAndPrintTask[0] pool-1-thread-1
SleepAndPrintTask[1] pool-1-thread-2
SleepAndPrintTask[4] pool-1-thread-5
SleepAndPrintTask[3] pool-1-thread-4
*/
