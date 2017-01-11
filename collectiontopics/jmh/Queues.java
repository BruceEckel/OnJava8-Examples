// collectiontopics/jmh/Queues.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Performance differences between Queues
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
public class Queues {
  private Queue<String> queue;

  @Param({
    "java.util.LinkedList",
    "java.util.concurrent.ConcurrentLinkedQueue",
    "java.util.concurrent.LinkedBlockingQueue",
    "java.util.concurrent.LinkedTransferQueue",
    "java.util.concurrent.PriorityBlockingQueue",
    "java.util.PriorityQueue",
    // Requires a size for construction:
    //"java.util.concurrent.ArrayBlockingQueue",
    // Won't accept more than one element:
    //"java.util.concurrent.SynchronousQueue",
    // Requires "Delayed" elements:
    //"java.util.concurrent.DelayQueue",
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
    queue = (Queue<String>)
      Class.forName(type).newInstance();
    for(int i = 0; i < size; i++)
      queue.add(Integer.toString(i));
  }
  @Benchmark
  public Queue<String> add() {
    queue.add("test");
    return queue;
  }
  @Benchmark
  public String poll() {
    return queue.poll();
  }
}
