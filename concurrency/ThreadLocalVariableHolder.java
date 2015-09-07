// concurrency/ThreadLocalVariableHolder.java
// ©2015 MindView LLC: see Copyright.txt
// Automatically giving each thread its own storage.
import java.util.concurrent.*;
import java.util.*;

class Accessor implements Runnable {
  private final int id;
  public Accessor(int idn) { id = idn; }
  @Override
  public void run() {
    while(!Thread.currentThread().isInterrupted()) {
      ThreadLocalVariableHolder.increment();
      System.out.println(this);
      Thread.yield();
    }
  }
  @Override
  public String toString() {
    return "#" + id + ": " +
      ThreadLocalVariableHolder.get();
  }
}

public class ThreadLocalVariableHolder {
  private static ThreadLocal<Integer> value =
    new ThreadLocal<Integer>() {
      private Random rand = new Random(47);
      @Override
      protected synchronized Integer initialValue() {
        return rand.nextInt(10000);
      }
    };
  public static void increment() {
    value.set(value.get() + 1);
  }
  public static int get() { return value.get(); }
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++)
      exec.execute(new Accessor(i));
    TimeUnit.SECONDS.sleep(3);  // Run for a while
    exec.shutdownNow();         // All Accessors will quit
  }
}
/* Output: (First and last 10 Lines)
#2: 9259
#1: 962
#3: 1862
#4: 6694
#0: 556
#4: 6695
#4: 6696
#3: 1863
#1: 963
#2: 9260
________...________...________...________...________
#3: 3791
#2: 11096
#4: 8402
#4: 8403
#2: 11097
#3: 3792
#0: 2637
#1: 3032
#4: 8404
#2: 11098
*/
