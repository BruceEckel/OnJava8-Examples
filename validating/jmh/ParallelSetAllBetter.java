// validating/jmh/ParallelSetAllBetter.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package validating.jmh;
import java.util.*;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class ParallelSetAllBetter {
  private long[] la;
  @Param({"1", "100", "10000", "1000000", "20000000"})
  int count;

  @Setup
  public void setup() {
    la = new long[count];
  }
  @Benchmark
  public void setAll() {
    Arrays.setAll(la, n -> n);
  }
  @Benchmark
  public void parallelSetAll() {
    Arrays.parallelSetAll(la, n -> n);
  }
}
