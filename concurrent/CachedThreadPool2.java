// concurrent/CachedThreadPool2.java
// (c)2021 MindView LLC: see Copyright.txt
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
3 pool-1-thread-4 100
2 pool-1-thread-3 200
6 pool-1-thread-7 300
7 pool-1-thread-8 400
0 pool-1-thread-1 514
1 pool-1-thread-2 527
4 pool-1-thread-5 627
5 pool-1-thread-6 727
9 pool-1-thread-10 827
8 pool-1-thread-9 927
*/
