// concurrent/DiningPhilosophers.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Deadlock can be hidden in a program
import java.util.stream.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;

public class DiningPhilosophers {
  static void stopAfter(int secs) {
    ScheduledExecutorService sched =
      Executors.newScheduledThreadPool(1);
    sched.schedule( () -> {
      System.out.println("Timeout");
      Philosopher.running.set(false);
      sched.shutdownNow();
      System.exit(0);
    }, secs, SECONDS);
  }
  public static void main(String[] args)
  throws Exception {
    stopAfter(3);
    try {
      IntStream.range(0, Philosopher.QUANTITY)
        .mapToObj(Philosopher::new)
        .map(CompletableFuture::runAsync)
        .collect(Collectors.toList())
        .forEach(CompletableFuture::join);
    } catch(CompletionException ex) {
      Philosopher.running.set(false);
      System.out.println("Broken out of deadlock");
      System.out.println("...");
    }
    Philosopher.trace.stream()
      .skip(Philosopher.trace.size() - 20)
      .forEach(System.out::println);
  }
}
/* Output:
Broken out of deadlock
...
P2 grabbing right
P1 grabbing left
P3 eating
P3 thinking
P3 grabbing right
P4 eating
P2 grabbing left
P4 thinking
P0 eating
P4 grabbing right
P3 grabbing left
P0 thinking
P0 grabbing right
P4 grabbing left
P0 grabbing left
P1 eating
P2 eating
P3 eating
P0 eating
P4 eating
Timeout
*/
