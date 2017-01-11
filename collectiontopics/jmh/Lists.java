// collectiontopics/jmh/Lists.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Performance differences between Lists
package collectiontopics.jmh;
import org.openjdk.jmh.annotations.*;
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

  private int middle;
  private ListIterator<String> it;

  @Setup
  @SuppressWarnings("unchecked")
  public void setup() throws Exception {
    list = (List<String>)
      Class.forName(type).newInstance();
    for(int i = 0; i < size; i++)
      list.add(Integer.toString(i));
    middle = size / 2;
    it = list.listIterator(middle);
  }
  @Benchmark
  public List<String> append() {
    list.add("test");
    return list;
  }
  @Benchmark
  public List<String> insert() {
    list.add(middle, "test");
    return list;
  }
  @Benchmark
  public String get() {
    return list.get(middle);
  }
  @Benchmark
  public List<String> set() {
    list.set(middle, "test");
    return list;
  }
  @Benchmark
  public List<String> iteradd() {
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
  public List<String> remove() {
    middle = list.size() / 2;
    if(middle - 1 > 0)
      try {
        list.remove(middle - 1);
      } catch(ArrayIndexOutOfBoundsException e) {
        System.out.println("Out of bounds -> size: "
          + list.size() + " middle - 1: " + (middle - 1) +
          " for " + list.getClass().getSimpleName());
      }
    return list;
  }
}
