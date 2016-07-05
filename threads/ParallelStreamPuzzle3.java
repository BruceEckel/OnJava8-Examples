// threads/ParallelStreamPuzzle3.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.stream.*;

public class ParallelStreamPuzzle3 {
  public static void main(String[] args) {
    List<Integer> x = IntStream.range(0, 30)
      .peek(e -> System.out.println(e + ": " +
        Thread.currentThread().getName()))
      .limit(10)
      .parallel()
      .boxed()
      .collect(Collectors.toList());
    System.out.println(x);
  }
}
/* Output:
1: ForkJoinPool.commonPool-worker-3
2: ForkJoinPool.commonPool-worker-6
4: ForkJoinPool.commonPool-worker-1
3: ForkJoinPool.commonPool-worker-7
8: main
0: ForkJoinPool.commonPool-worker-4
6: ForkJoinPool.commonPool-worker-5
9: ForkJoinPool.commonPool-worker-2
7: ForkJoinPool.commonPool-worker-6
5: ForkJoinPool.commonPool-worker-3
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
*/
