// understandingcollections/jmh/Lists.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Performance differences between Lists
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
public class Lists {
  private List<String> list;

  @Param({
    "java.util.ArrayList",
    "java.util.Vector",
    "java.util.LinkedList",
    "java.util.concurrent.CopyOnWriteArrayList"
  })
  private String type;

  @Param({
    "1",
    "10",
    "100",
    "1000",
    "10000",
    "100000"
  })
  private int size;

  @Setup
  public void setup() {
    try {
      list = (List<String>)
        Class.forName(type).newInstance();
    } catch(Exception e) {
      System.err.println(
        "-> Cannot create: " + type);
      System.exit(99);
    }
    for(int i = 0; i < size; i++)
      list.add(Integer.toString(i));
  }
  @Benchmark
  public List<String> add() {
    list.add(list.size() / 2, "test");
    return list;
  }
  @Benchmark
  public void get(Blackhole bh) {
    bh.consume(list.get(list.size() / 2));
  }
  @Benchmark
  public List<String> set() {
    list.set(list.size() / 2, "test");
    return list;
  }
  @Benchmark
  public List<String> iteradd() {
    ListIterator<String> it =
      list.listIterator(list.size() / 2);
    try {
      it.add("test");
    } catch(UnsupportedOperationException e) {
      System.err.println(
        "-> Unsupported: listIterator.add() in " +
        list.getClass().getSimpleName());
    }
    return list;
  }
  @Benchmark
  public List<String> insert() {
    list.add(list.size() / 2, "test");
    return list;
  }
  @Benchmark
  public List<String> remove() {
    int index = list.size() / 2;
    if(index > 0)
      list.remove(index);
    return list;
  }
}
