// lowlevel/PriorityBlockingQueueDemo.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.util.*;
import onjava.Nap;

class PrioritizedTask implements
Runnable, Comparable<PrioritizedTask>  {
  private SplittableRandom rand = new SplittableRandom(47);
  private static int counter = 0;
  private final int id = counter++;
  private final int priority;
  protected static List<PrioritizedTask> sequence =
    new ArrayList<>();
  public PrioritizedTask(int priority) {
    this.priority = priority;
    sequence.add(this);
  }
  @Override
  public int compareTo(PrioritizedTask arg) {
    return priority < arg.priority ? 1 :
      (priority > arg.priority ? -1 : 0);
  }
  @Override
  public void run() {
    new Nap(rand.nextInt(250));
    System.out.println(this);
  }
  @Override
  public String toString() {
    return String.format("[%1$-3d]", priority) +
      " Task " + id;
  }
  public String summary() {
    return "(" + id + ":" + priority + ")";
  }
  public static
  class EndSentinel extends PrioritizedTask {
    private ExecutorService exec;
    public EndSentinel(ExecutorService e) {
      super(-1); // Lowest priority in this program
      exec = e;
    }
    @Override
    public void run() {
      int count = 0;
      for(PrioritizedTask pt : sequence) {
        System.out.print(pt.summary());
        if(++count % 5 == 0)
          System.out.println();
      }
      System.out.println();
      System.out.println(this + " Calling shutdownNow()");
      exec.shutdownNow();
    }
  }
}

class PrioritizedTaskProducer implements Runnable {
  private SplittableRandom rand = new SplittableRandom(47);
  private Queue<Runnable> queue;
  private ExecutorService exec;
  public PrioritizedTaskProducer(
    Queue<Runnable> q, ExecutorService e) {
    queue = q;
    exec = e; // Used for EndSentinel
  }
  @Override
  public void run() {
    // Unbounded queue; never blocks.
    // Fill it up fast with random priorities:
    for(int i = 0; i < 20; i++) {
      queue.add(new PrioritizedTask(rand.nextInt(10)));
      Thread.yield();
    }
    // Trickle in highest-priority jobs:
    for(int i = 0; i < 10; i++) {
      new Nap(250);
      queue.add(new PrioritizedTask(10));
    }
    // Add jobs, lowest priority first:
    for(int i = 0; i < 10; i++)
      queue.add(new PrioritizedTask(i));
    // A sentinel to stop all the tasks:
    queue.add(new PrioritizedTask.EndSentinel(exec));
    System.out.println(
      "Finished PrioritizedTaskProducer");
  }
}

class PrioritizedTaskConsumer implements Runnable {
  private PriorityBlockingQueue<Runnable> q;
  public PrioritizedTaskConsumer(
    PriorityBlockingQueue<Runnable> q) {
    this.q = q;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted())
        // Use current thread to run the task:
        q.take().run();
    } catch(InterruptedException e) {
      // Acceptable way to exit
    }
    System.out.println(
      "Finished PrioritizedTaskConsumer");
  }
}

public class PriorityBlockingQueueDemo {
  public static void
  main(String[] args) throws Exception {
    ExecutorService es = Executors.newCachedThreadPool();
    PriorityBlockingQueue<Runnable> queue =
      new PriorityBlockingQueue<>();
    es.execute(new PrioritizedTaskProducer(queue, es));
    es.execute(new PrioritizedTaskConsumer(queue));
  }
}
/* Output: (First and Last 12 Lines)
[9  ] Task 5
[8  ] Task 7
[10 ] Task 20
[8  ] Task 8
[10 ] Task 21
[7  ] Task 4
[10 ] Task 22
[7  ] Task 19
[10 ] Task 23
[7  ] Task 11
[10 ] Task 24
[7  ] Task 1
...________...________...________...________...
[0  ] Task 16
(0:5)(1:7)(2:1)(3:0)(4:7)
(5:9)(6:6)(7:8)(8:8)(9:3)
(10:0)(11:7)(12:0)(13:5)(14:2)
(15:4)(16:0)(17:2)(18:1)(19:7)
(20:10)(21:10)(22:10)(23:10)(24:10)
(25:10)(26:10)(27:10)(28:10)(29:10)
(30:0)(31:1)(32:2)(33:3)(34:4)
(35:5)(36:6)(37:7)(38:8)(39:9)
(40:-1)
[-1 ] Task 40 Calling shutdownNow()
Finished PrioritizedTaskConsumer
*/
