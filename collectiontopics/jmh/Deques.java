// collectiontopics/jmh/Deques.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Performance differences between Deques
package understandingcollections.jmh;
import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;
import java.util.*;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, batchSize = 5000)
@Measurement(iterations = 5, batchSize = 5000)
@BenchmarkMode(Mode.SingleShotTime)
@Fork(1)
public class Deques {
  private Deque<String> deque;

  @Param({
    "java.util.LinkedList",
    "java.util.ArrayDeque",
    "java.util.concurrent.ConcurrentLinkedDeque",
    "java.util.concurrent.LinkedBlockingDeque",
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
  @SuppressWarnings("unchecked")
  public void setup() throws Exception {
    deque = (Deque<String>)
      Class.forName(type).newInstance();
    for(int i = 0; i < size; i++)
      deque.add(Integer.toString(i));
  }
  @Benchmark
  public Deque<String> addFirst() {
    deque.addFirst("test");
    return deque;
  }
  @Benchmark
  public Deque<String> addLast() {
    deque.addLast("test");
    return deque;
  }
  @Benchmark
  public String pollFirst() {
    return deque.pollFirst();
  }
  @Benchmark
  public String pollLast() {
    return deque.pollLast();
  }
}
