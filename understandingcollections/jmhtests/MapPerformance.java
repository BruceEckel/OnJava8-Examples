// understandingcollections/jmhtests/MapPerformance.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Performance differences between Maps
package understandingcollections.jmhtests;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(NANOSECONDS)
public class MapPerformance {
  private Map<Integer, Integer> map;

  @Param({"hashmap", "treemap", "linkedhashmap",
    "identityhashmap", "weakhashmap", "hashtable"})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    switch(type) {
      case "hashmap":
        map = new HashMap<>();
        break;
      case "treemap":
        map = new TreeMap<>();
        break;
      case "linkedhashmap":
        map = new LinkedHashMap<>();
        break;
      case "identityhashmap":
        map = new IdentityHashMap<>();
        break;
      case "weakhashmap":
        map = new WeakHashMap<>();
        break;
      case "hashtable":
        map = new Hashtable<>();
        break;
      default:
        throw new IllegalStateException("Unknown " + type);
    }

    begin = 1;
    end = 256;
    for (int i = begin; i < end; i++) {
      map.put(i, i);
    }
  }

  @Benchmark
  public void get(Blackhole bh) {
    for (int i = begin; i < end; i++) {
      bh.consume(map.get(i));
    }
  }

  @Benchmark
  public void put(Blackhole bh) {
    for (int i = begin; i < end; i++) {
      bh.consume(map.put(i, i));
    }
  }

  @Benchmark
  public void iterate(Blackhole bh) {
    Iterator it = map.entrySet().iterator();
    while(it.hasNext())
      bh.consume(it.next());
  }

}
