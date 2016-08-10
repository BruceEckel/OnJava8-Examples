// verifying/jmhtests/ParallelSetAll.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package verifying.jmhtests;
import java.util.*;
import org.openjdk.jmh.annotations.*;

@State(Scope.Thread)
public class ParallelSetAll {
  private long[] la;
  @Setup
  public void setup() {
    la = new long[20_000_000];
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
