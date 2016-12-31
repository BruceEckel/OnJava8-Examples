// lowlevel/DelayQueueDemo.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.util.*;
import static java.util.concurrent.TimeUnit.*;

class DelayedTask implements Runnable, Delayed {
  private static int counter = 0;
  private final int id = counter++;
  private final int delta;
  private final long trigger;
  protected static List<DelayedTask> sequence =
    new ArrayList<>();
  public DelayedTask(int delayInMilliseconds) {
    delta = delayInMilliseconds;
    trigger = System.nanoTime() +
      NANOSECONDS.convert(delta, MILLISECONDS);
    sequence.add(this);
  }
  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(
      trigger - System.nanoTime(), NANOSECONDS);
  }
  @Override
  public int compareTo(Delayed arg) {
    DelayedTask that = (DelayedTask)arg;
    if(trigger < that.trigger) return -1;
    if(trigger > that.trigger) return 1;
    return 0;
  }
  @Override
  public void run() { System.out.print(this + " "); }
  @Override
  public String toString() {
    return String.format("[%1$-4d]", delta) +
      " Task " + id;
  }
  public String summary() {
    return "(" + id + ":" + delta + ")";
  }
  public static class EndSentinel extends DelayedTask {
    private ExecutorService exec;
    public EndSentinel(int delay, ExecutorService e) {
      super(delay);
      exec = e;
    }
    @Override
    public void run() {
      for(DelayedTask pt : sequence) {
        System.out.print(pt.summary() + " ");
      }
      System.out.println();
      System.out.println(this + " Calling shutdownNow()");
      exec.shutdownNow();
    }
  }
}

class DelayedTaskConsumer implements Runnable {
  private DelayQueue<DelayedTask> q;
  public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
    this.q = q;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted())
        q.take().run(); // Run task with current thread
    } catch(InterruptedException e) {
      // Acceptable way to exit
    }
    System.out.println("Finished DelayedTaskConsumer");
  }
}

public class DelayQueueDemo {
  public static void main(String[] args) {
    SplittableRandom rand = new SplittableRandom(47);
    ExecutorService es = Executors.newCachedThreadPool();
    DelayQueue<DelayedTask> queue =
      new DelayQueue<>();
    // Fill with tasks that have random delays:
    for(int i = 0; i < 20; i++)
      queue.put(new DelayedTask(rand.nextInt(5000)));
    // Set the stopping point
    queue.add(new DelayedTask.EndSentinel(5000, es));
    es.execute(new DelayedTaskConsumer(queue));
  }
}
/* Output:
[70  ] Task 10 [125 ] Task 13 [267 ] Task 19 [635 ] Task 0
[650 ] Task 16 [682 ] Task 17 [807 ] Task 11 [1131] Task 18
[1177] Task 4 [1193] Task 9 [1634] Task 15 [1656] Task 6
[2400] Task 12 [3479] Task 5 [3737] Task 1 [3768] Task 7
[3941] Task 2 [4720] Task 3 [4762] Task 14 [4948] Task 8
(0:635) (1:3737) (2:3941) (3:4720) (4:1177) (5:3479)
(6:1656) (7:3768) (8:4948) (9:1193) (10:70) (11:807)
(12:2400) (13:125) (14:4762) (15:1634) (16:650) (17:682)
(18:1131) (19:267) (20:5000)
[5000] Task 20 Calling shutdownNow()
Finished DelayedTaskConsumer
*/
