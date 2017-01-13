// lowlevel/PriorityBlockingQueueDemo.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import onjava.Nap;

class Prioritized implements Comparable<Prioritized>  {
  private static AtomicInteger counter =
    new AtomicInteger();
  private final int id = counter.getAndAdd(1);
  private final int priority;
  private static List<Prioritized> sequence =
    new CopyOnWriteArrayList<>();
  public Prioritized(int priority) {
    this.priority = priority;
    sequence.add(this);
  }
  @Override
  public int compareTo(Prioritized arg) {
    return priority < arg.priority ? 1 :
      (priority > arg.priority ? -1 : 0);
  }
  @Override
  public String toString() {
    return String.format(
      "[%d] Prioritized %d", priority, id);
  }
  public void displaySequence() {
    int count = 0;
    for(Prioritized pt : sequence) {
      System.out.printf("(%d:%d)", pt.id, pt.priority);
      if(++count % 5 == 0)
        System.out.println();
    }
  }
  public static class EndSentinel extends Prioritized {
    public EndSentinel() { super(-1); }
  }
}

class Producer implements Runnable {
  private static AtomicInteger seed =
    new AtomicInteger(47);
  private SplittableRandom rand =
    new SplittableRandom(seed.getAndAdd(10));
  private Queue<Prioritized> queue;
  public Producer(Queue<Prioritized> q) {
    queue = q;
  }
  @Override
  public void run() {
    rand.ints(10, 0, 20)
      .mapToObj(Prioritized::new)
      .peek(p -> new Nap(rand.nextInt(
        PriorityBlockingQueueDemo.NAPTIME)))
      .forEach(p -> queue.add(p));
    queue.add(new Prioritized.EndSentinel());
  }
}

class Consumer implements Runnable {
  private PriorityBlockingQueue<Prioritized> q;
  private SplittableRandom rand =
    new SplittableRandom(47);
  public
  Consumer(PriorityBlockingQueue<Prioritized> q) {
    this.q = q;
  }
  @Override
  public void run() {
    while(true) {
      try {
        Prioritized pt = q.take();
        System.out.println(pt);
        if(pt instanceof Prioritized.EndSentinel) {
          pt.displaySequence();
          break;
        }
        new Nap(rand.nextInt(
          PriorityBlockingQueueDemo.NAPTIME));
      } catch(InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

public class PriorityBlockingQueueDemo {
  static final int NAPTIME = 50;
  public static void main(String[] args) {
    PriorityBlockingQueue<Prioritized> queue =
      new PriorityBlockingQueue<>();
    CompletableFuture.runAsync(new Producer(queue));
    CompletableFuture.runAsync(new Producer(queue));
    CompletableFuture.runAsync(new Producer(queue));
    CompletableFuture.runAsync(new Consumer(queue))
      .join();
  }
}
/* Output:
[12] Prioritized 1
[17] Prioritized 2
[15] Prioritized 0
[18] Prioritized 17
[17] Prioritized 10
[16] Prioritized 20
[16] Prioritized 16
[15] Prioritized 15
[14] Prioritized 8
[14] Prioritized 13
[13] Prioritized 12
[12] Prioritized 19
[12] Prioritized 14
[11] Prioritized 6
[11] Prioritized 22
[11] Prioritized 4
[11] Prioritized 31
[10] Prioritized 30
[10] Prioritized 26
[8] Prioritized 18
[8] Prioritized 23
[8] Prioritized 9
[6] Prioritized 24
[3] Prioritized 3
[2] Prioritized 29
[1] Prioritized 7
[0] Prioritized 27
[0] Prioritized 5
[0] Prioritized 21
[0] Prioritized 11
[-1] Prioritized 25
(0:15)(2:17)(1:12)(3:3)(4:11)
(5:0)(6:11)(7:1)(8:14)(9:8)
(10:17)(11:0)(12:13)(13:14)(14:12)
(15:15)(16:16)(17:18)(18:8)(19:12)
(20:16)(21:0)(22:11)(23:8)(24:6)
(25:-1)(26:10)(27:0)(28:-1)(29:2)
(30:10)(31:11)(32:-1)
*/
