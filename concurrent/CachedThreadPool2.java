// concurrent/CachedThreadPool2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.util.stream.*;

public class CachedThreadPool2 {
  public static void main(String[] args) {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    IntStream.range(0, 10)
      .mapToObj(InterferingTask::new)
      .forEach(exec::execute);
    exec.shutdown();
  }
}
/* Output:
1 pool-1-thread-2 117
0 pool-1-thread-1 117
2 pool-1-thread-3 217
4 pool-1-thread-5 407
3 pool-1-thread-4 337
5 pool-1-thread-6 507
7 pool-1-thread-8 707
6 pool-1-thread-7 607
8 pool-1-thread-9 807
9 pool-1-thread-10 907
*/
