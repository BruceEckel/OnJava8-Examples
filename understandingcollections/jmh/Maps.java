// understandingcollections/jmh/Maps.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Performance differences between Maps
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
public class Maps {
  private Map<String,String> map;

  @Param({
    "java.util.HashMap",
    "java.util.Hashtable",
    "java.util.TreeMap",
    "java.util.LinkedHashMap",
    "java.util.IdentityHashMap",
    "java.util.WeakHashMap",
    "java.util.concurrent.ConcurrentHashMap",
    "java.util.concurrent.ConcurrentSkipListMap",
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
    map = (Map<String,String>)
      Class.forName(type).newInstance();
    for(int i = 0; i < size; i++)
      map.put(Integer.toString(i), Integer.toString(i));
    key = Integer.toString(size / 2);
  }
  @Benchmark
  public boolean containsKey() {
    return map.containsKey(key);
  }
  @Benchmark
  public String get() {
    return map.get(key);
  }
  @Benchmark
  public Map<String,String> put() {
    map.put("test", "test");
    return map;
  }
  @Benchmark
  public void iterate(Blackhole bh) {
    Iterator it = map.entrySet().iterator();
    while(it.hasNext())
      bh.consume(it.next());
  }
}
