// threads/CachedThreadPool2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;

public class CachedThreadPool2 {
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    for(int id = 0; id < 10; id++)
      exec.execute(new InterferingTask(id));
    exec.shutdown();
    exec.awaitTermination(5, TimeUnit.SECONDS);
  }
}
/* Output:
3 pool-1-thread-4 301
7 pool-1-thread-8 684
6 pool-1-thread-7 584
5 pool-1-thread-6 527
1 pool-1-thread-2 301
2 pool-1-thread-3 301
0 pool-1-thread-1 301
4 pool-1-thread-5 401
9 pool-1-thread-10 884
8 pool-1-thread-9 784
*/
