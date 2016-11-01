// understandingcollections/jmh/Sets.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates performance differences in Sets
package understandingcollections.jmh;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.*;
import java.util.concurrent.*;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, batchSize = 5000)
@Measurement(iterations = 5, batchSize = 5000)
@BenchmarkMode(Mode.SingleShotTime)
@Fork(1)
public class Sets {
  private Set<String> set;

  @Param({
    "java.util.HashSet",
    "java.util.TreeSet",
    "java.util.LinkedHashSet",
    "java.util.concurrent.ConcurrentSkipListSet",
    "java.util.concurrent.CopyOnWriteArraySet",
  })
  private String type;

  @Param({
    "1",
    "10",
    "100",
    "1000",
    "10000",
  })
  private int size;

  private String key;

  @Setup
  public void setup() throws Exception {
    set = (Set<String>)
      Class.forName(type).newInstance();
    for(int i = 0; i < size; i++)
      set.add(Integer.toString(i));
    key = Integer.toString(size/2);
  }
  @Benchmark
  public Set<String> add() {
    set.add("test");
    return set;
  }
  @Benchmark
  public boolean contains() {
    return set.contains(key);
  }
  @Benchmark
  public void iterate(Blackhole bh) {
    Iterator<String> it = set.iterator();
    while(it.hasNext())
      bh.consume(it.next());
  }
}
