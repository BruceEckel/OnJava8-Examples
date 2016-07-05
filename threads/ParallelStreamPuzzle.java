// threads/ParallelStreamPuzzle.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ParallelStreamPuzzle {
  static class IntGenerator
  implements Supplier<Integer> {
    private int current = 0;
    public Integer get() {
      return current++;
    }
  }
  public static void main(String[] args) {
    List<Integer> x =
      Stream.generate(new IntGenerator())
        .limit(10)
        .parallel()  // (1)
        .collect(Collectors.toList());
    System.out.println(x);
  }
}
