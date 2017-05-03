// concurrent/CountingStream.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class CountingStream {
  public static void main(String[] args) {
    System.out.println(
      IntStream.range(0, 10)
        .parallel()
        .mapToObj(CountingTask::new)
        .map(ct -> ct.call())
        .reduce(0, Integer::sum));
  }
}
/* Output:
2 ForkJoinPool.commonPool-worker-1 100
8 ForkJoinPool.commonPool-worker-2 100
4 ForkJoinPool.commonPool-worker-1 100
3 ForkJoinPool.commonPool-worker-1 100
7 ForkJoinPool.commonPool-worker-1 100
0 ForkJoinPool.commonPool-worker-1 100
5 ForkJoinPool.commonPool-worker-1 100
6 main 100
1 ForkJoinPool.commonPool-worker-3 100
9 ForkJoinPool.commonPool-worker-2 100
1000
*/
