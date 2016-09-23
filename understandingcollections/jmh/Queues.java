// understandingcollections/jmh/Queues.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Demonstrates performance differences in Queues
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
public class Queues {
  private LinkedList<Integer> queue;

  @Param({"LinkedList"})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    switch(type) {
      case "LinkedList":
        queue = new LinkedList<>();
        break;
      default:
        throw new IllegalStateException("Unknown " + type);
    }
    begin = 1;
    end = 256;
    for(int i = begin; i < end; i++) {
      queue.add(i);
    }
  }
  @Benchmark
  public void queue_addFirst() {
    for(int i = begin; i < end; i++)
      queue.addFirst(47);
  }
  @Benchmark
  public void queue_addLast() {
    for(int i = begin; i < end; i++)
      queue.addLast(47);
  }
  @Benchmark
  public void queue_removeFirst(Blackhole bh) {
    while(queue.size() > 0)
      bh.consume(queue.removeFirst());
  }
  @Benchmark
  public void queue_removeLast(Blackhole bh) {
    while(queue.size() > 0)
      bh.consume(queue.removeLast());
  }
}
