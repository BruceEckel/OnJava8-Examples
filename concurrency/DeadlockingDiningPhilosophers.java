// concurrency/DeadlockingDiningPhilosophers.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstrates how deadlock can be hidden in a program.
// {Args: 0 5 timeout}
import java.util.concurrent.*;

public class DeadlockingDiningPhilosophers {
  public static void main(String[] args) throws Exception {
    int ponder = 5;
    if(args.length > 0)
      ponder = Integer.parseInt(args[0]);
    int size = 5;
    if(args.length > 1)
      size = Integer.parseInt(args[1]);
    ExecutorService exec = Executors.newCachedThreadPool();
    Chopstick[] sticks = new Chopstick[size];
    for(int i = 0; i < size; i++)
      sticks[i] = new Chopstick();
    for(int i = 0; i < size; i++)
      exec.execute(new Philosopher(
        sticks[i], sticks[(i+1) % size], i, ponder));
    if(args.length == 3 && args[2].equals("timeout"))
      TimeUnit.SECONDS.sleep(5);
    else {
      System.out.println("Press 'Enter' to quit");
      System.in.read();
    }
    exec.shutdownNow();
  }
}
/* Output: (First and last 10 Lines)
Philosopher 1 thinking
Philosopher 1 grabbing right
Philosopher 1 grabbing left
Philosopher 1 eating
Philosopher 1 thinking
Philosopher 3 thinking
Philosopher 4 thinking
Philosopher 2 thinking
Philosopher 1 grabbing right
Philosopher 0 thinking
________...________...________...________...________
Philosopher 3 grabbing left
Philosopher 2 grabbing left
Philosopher 0 grabbing left
Philosopher 4 grabbing left
Philosopher 1 grabbing left
Philosopher 3 exiting via interrupt
Philosopher 4 exiting via interrupt
Philosopher 1 exiting via interrupt
Philosopher 0 exiting via interrupt
Philosopher 2 exiting via interrupt
*/
