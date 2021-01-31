// concurrent/ParallelStreamPuzzle2.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.nio.file.*;

public class ParallelStreamPuzzle2 {
  public static final Deque<String> trace =
    new ConcurrentLinkedDeque<>();
  static class
  IntGenerator implements Supplier<Integer> {
    private AtomicInteger current =
      new AtomicInteger();
    @Override public Integer get() {
      trace.add(current.get() + ": " +
        Thread.currentThread().getName());
      return current.getAndIncrement();
    }
  }
  public static void
  main(String[] args) throws Exception {
    List<Integer> x =
      Stream.generate(new IntGenerator())
        .limit(10)
        .parallel()
        .collect(Collectors.toList());
    System.out.println(x);
    Files.write(Paths.get("PSP2.txt"), trace);
  }
}
/* Output:
[1, 5, 7, 8, 9, 11, 13, 15, 18, 21]
*/
