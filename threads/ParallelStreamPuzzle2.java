// threads/ParallelStreamPuzzle2.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.nio.file.*;

public class ParallelStreamPuzzle2 {
  public static ConcurrentLinkedDeque<String> trace =
    new ConcurrentLinkedDeque<>();
  static class
  IntGenerator implements Supplier<Integer> {
    private int current = 0;
    public synchronized Integer get() {  // (1)
      trace.add(current + ": " +
        Thread.currentThread().getName());
      return current++;
    }
  }
  public static void main(String[] args) throws Exception {
    List<Integer> x = Stream.generate(new IntGenerator())
      .limit(10)
      .parallel()
      .collect(Collectors.toList());
    System.out.println(x);
    Files.write(Paths.get("PSP2.txt"), trace);
  }
}
/* Output:
[2, 3, 4, 5, 6, 7, 8, 9, 10, 63]
*/
