// concurrent/Philosopher.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
import java.util.concurrent.atomic.*;

class Philosopher implements Runnable {
  static class Chopstick {}
  public static final int QUANTITY = 5;
  static Queue<String> trace =
    new ConcurrentLinkedQueue<>();
  static AtomicBoolean running =
    new AtomicBoolean(true);
  public static
  List<BlockingQueue<Chopstick>> chopsticks =
    IntStream.range(0, Philosopher.QUANTITY)
      .mapToObj(i -> {
        BlockingQueue<Chopstick> bd =
          new ArrayBlockingQueue<>(1);
        bd.add(new Chopstick());
        return bd;
      })
      .collect(Collectors.toList());
  private final int seatNumber;
  private final int left, right;
  public Philosopher(int seatNumber) {
    this.seatNumber = left = seatNumber;
    right = (seatNumber + 1) % QUANTITY;
  }
  @Override
  public void run() {
    try {
      while(running.get()) {
        trace.add(this + " thinking");
        // Philosopher becomes hungry
        trace.add(this + " grabbing right");
        Chopstick rightStick =
          chopsticks.get(right).poll(2, SECONDS);
        trace.add(this + " grabbing left");
        Chopstick leftStick =
          chopsticks.get(left).poll(2, SECONDS);
        trace.add(this + " eating");
        // Finished, return chopsticks to table:
        chopsticks.get(right).put(rightStick);
        chopsticks.get(left).put(leftStick);
      }
    } catch(InterruptedException e) {
      trace.add("exiting via interrupt");
    }
  }
  @Override
  public String toString() {
    return "P" + seatNumber;
  }
}
