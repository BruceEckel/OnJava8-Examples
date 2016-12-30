// concurrent/DiningPhilosophers.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Hidden deadlock
import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

public class DiningPhilosophers {
  private StickHolder[] sticks;
  private Philosopher[] philosophers;
  public DiningPhilosophers(int n) {
    sticks = new StickHolder[n];
    Arrays.setAll(sticks, i -> new StickHolder());
    philosophers = new Philosopher[n];
    Arrays.setAll(philosophers, i ->
      new Philosopher(i,
        sticks[i], sticks[(i + 1) % n]));    // [1]
    // Fix by reversing stick order:
    // philosophers[1] =                     // [2]
    //   new Philosopher(0, sticks[0], sticks[1]);
    Arrays.stream(philosophers)
      .forEach(CompletableFuture::runAsync); // [3]
  }
  public static void main(String[] args) {
    // Returns right away:
    new DiningPhilosophers(5);               // [4]
    // Keeps main() from exiting:
    ScheduledExecutorService sched =
      Executors.newScheduledThreadPool(1);
    sched.schedule( () -> {
      System.out.println("Shutdown");
      sched.shutdown();
    }, 3, SECONDS);
  }
}
