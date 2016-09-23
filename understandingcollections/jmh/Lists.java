// understandingcollections/jmh/Lists.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates performance differences in Lists
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
public class Lists {
  private List<Integer> list;

  @Param({"ArrayList", "LinkedList", "Vector"})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    switch(type) {
      case "ArrayList":
        list = new ArrayList<>();
        break;
      case "LinkedList":
        list = new LinkedList<>();
        break;
      case "Vector":
        list = new Vector<>();
        break;
      default:
        throw new IllegalStateException("Unknown " + type);
    }
    begin = 0;
    end = 256;
    for(int i = begin; i < end; i++) {
      list.add(i);
    }
  }
  @Benchmark
  public void add() {
    for(int i = begin; i < end; i++)
      list.add(i);
  }
  @Benchmark
  public void get(Blackhole bh) {
    for(int i = begin; i < end; i++)
      bh.consume(list.get(i));
  }
  @Benchmark
  public void set() {
    for(int i = begin; i < end; i++)
      list.set(i, 47);
  }
  @Benchmark
  public void iteradd() {
    int half = list.size() / 2;
    ListIterator<Integer> it =
      list.listIterator(half);
    for(int i = begin; i < end; i++)
      it.add(47);
  }
  @Benchmark
  public void insert() {
    for(int i = begin; i < end; i++)
      list.add(5, 47);
  }
  @Benchmark
  public void remove() {
    while(list.size() > 5)
      list.remove(5);
  }
}
