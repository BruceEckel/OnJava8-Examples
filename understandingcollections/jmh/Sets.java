// understandingcollections/jmh/Sets.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates performance differences in Sets
package understandingcollections.jmh;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(MICROSECONDS)
public class Sets {
  private Set<Integer> set;

  @Param({"HashSet", "TreeSet", "LinkedHashSet"})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    switch(type) {
      case "HashSet":
        set = new HashSet<>();
        break;
      case "TreeSet":
        set = new TreeSet<>();
        break;
      case "LinkedHashSet":
        set = new LinkedHashSet<>();
        break;
      default:
        throw new IllegalStateException("Unknown " + type);
    }
    begin = 1;
    end = 256;
    for (int i = begin; i < end; i++)
      set.add(i);
  }
  @Benchmark
  public void add() {
    for(int i = begin; i < end; i++)
      set.add(i);
  }
  @Benchmark
  public void contains(Blackhole bh) {
    for(int i = begin; i < end; i++)
      bh.consume(set.contains(i));
  }
  @Benchmark
  public void iterate(Blackhole bh) {
    Iterator<Integer> it = set.iterator();
    while(it.hasNext())
      bh.consume(it.next());
  }
}
