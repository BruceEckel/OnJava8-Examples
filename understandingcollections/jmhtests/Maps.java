// understandingcollections/jmhtests/Maps.java
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
public class Maps {
  private Map<Integer, Integer> map;

  @Param({"HashMap", "TreeMap", "LinkedHashMap",
    "IdentityHashMap", "WeakHashMap", "Hashtable",})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    switch(type) {
      case "HashMap":
        map = new HashMap<>();
        break;
      case "TreeMap":
        map = new TreeMap<>();
        break;
      case "LinkedHashMap":
        map = new LinkedHashMap<>();
        break;
      case "IdentityHashMap":
        map = new IdentityHashMap<>();
        break;
      case "WeakHashMap":
        map = new WeakHashMap<>();
        break;
      case "Hashtable":
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
  public void put() {
    for (int i = begin; i < end; i++) {
      map.put(i, i);
    }
  }

  @Benchmark
  public void iterate(Blackhole bh) {
    Iterator it = map.entrySet().iterator();
    while(it.hasNext())
      bh.consume(it.next());
  }

}
