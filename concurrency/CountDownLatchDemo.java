//: concurrency/CountDownLatchDemo.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.concurrent.*;
import java.util.*;
import static com.mindviewinc.util.Print.*;

// Performs some portion of a task:
class TaskPortion implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private static Random rand = new Random(47);
  private final CountDownLatch latch;
  TaskPortion(CountDownLatch latch) {
    this.latch = latch;
  }
  @Override
  public void run() {
    try {
      doWork();
      latch.countDown();
    } catch(InterruptedException ex) {
      // Acceptable way to exit
    }
  }
  public void doWork() throws InterruptedException {
    TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
    print(this + "completed");
  }
  @Override
  public String toString() {
    return String.format("%1$-3d ", id);
  }
}

// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
  private static int counter = 0;
  private final int id = counter++;
  private final CountDownLatch latch;
  WaitingTask(CountDownLatch latch) {
    this.latch = latch;
  }
  @Override
  public void run() {
    try {
      latch.await();
      print("Latch barrier passed for " + this);
    } catch(InterruptedException ex) {
      print(this + " interrupted");
    }
  }
  @Override
  public String toString() {
    return String.format("WaitingTask %1$-3d ", id);
  }
}

public class CountDownLatchDemo {
  static final int SIZE = 100;
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    // All must share a single CountDownLatch object:
    CountDownLatch latch = new CountDownLatch(SIZE);
    for(int i = 0; i < 10; i++)
      exec.execute(new WaitingTask(latch));
    for(int i = 0; i < SIZE; i++)
      exec.execute(new TaskPortion(latch));
    print("Launched all tasks");
    exec.shutdown(); // Quit when all tasks complete
  }
} /* Output: (First and last 10 Lines)
Launched all tasks
56  completed
99  completed
28  completed
92  completed
90  completed
8   completed
14  completed
71  completed
5   completed
________...________...________...________...________
Latch barrier passed for WaitingTask 0
Latch barrier passed for WaitingTask 2
Latch barrier passed for WaitingTask 8
Latch barrier passed for WaitingTask 9
Latch barrier passed for WaitingTask 6
Latch barrier passed for WaitingTask 5
Latch barrier passed for WaitingTask 4
Latch barrier passed for WaitingTask 3
Latch barrier passed for WaitingTask 1
Latch barrier passed for WaitingTask 7
*///:~
