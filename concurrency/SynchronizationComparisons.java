//: concurrency/SynchronizationComparisons.java
// Comparing the performance of explicit Locks
// and Atomics versus the synchronized keyword.
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;
import java.util.*;
import static net.mindview.util.Print.*;

abstract class Accumulator {
  public static long cycles = 50000L;
  // Number of Modifiers and Readers during each test:
  private static final int N = 4;
  public static ExecutorService exec =
    Executors.newFixedThreadPool(N*2);
  private static CyclicBarrier barrier =
    new CyclicBarrier(N*2 + 1);
  protected volatile int index = 0;
  protected volatile long value = 0;
  protected long duration = 0;
  protected String id = "error";
  protected final static int SIZE = 100000;
  protected static int[] preLoaded = new int[SIZE];
  static { // Load the array of random numbers:
    Random rand = new Random(47);
    for(int i = 0; i < SIZE; i++)
      preLoaded[i] = rand.nextInt();
  }
  public abstract void accumulate();
  public abstract long read();
  private class Modifier implements Runnable {
    @Override
    public void run() {
      for(long i = 0; i < cycles; i++)
        accumulate();
      try {
        barrier.await();
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
  private class Reader implements Runnable {
    private volatile long value;
    @Override
    public void run() {
      for(long i = 0; i < cycles; i++)
        value = read();
      try {
        barrier.await();
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
  public void timedTest() {
    long start = System.nanoTime();
    for(int i = 0; i < N; i++) {
      exec.execute(new Modifier());
      exec.execute(new Reader());
    }
    try {
      barrier.await();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
    duration = System.nanoTime() - start;
    printf("%-13s: %13d\n", id, duration);
  }
  public void report(Accumulator acc2) {
    printf("%-22s: %.2f\n", this.id + "/" + acc2.id,
      (double)this.duration/(double)acc2.duration);
  }
}

class SynchronizedTest extends Accumulator {
  { id = "synch"; }
  @Override
  public synchronized void accumulate() {
    value += preLoaded[index++];
    if(index >= SIZE) index = 0;
  }
  @Override
  public synchronized long read() {
    return value;
  }
}

class LockTest extends Accumulator {
  { id = "Lock"; }
  private Lock lock = new ReentrantLock();
  @Override
  public void accumulate() {
    lock.lock();
    try {
      value += preLoaded[index++];
      if(index >= SIZE) index = 0;
    } finally {
      lock.unlock();
    }
  }
  @Override
  public long read() {
    lock.lock();
    try {
      return value;
    } finally {
      lock.unlock();
    }
  }
}

class AtomicTest extends Accumulator {
  { id = "Atomic"; }
  private AtomicInteger index = new AtomicInteger(0);
  private AtomicLong value = new AtomicLong(0);
  // Relying on more than one Atomic at a time doesn't
  // work, so we still have to synchronize. But it gives
  // a performance indicator:
  @Override
  public synchronized void accumulate() {
    int i;
    i = index.getAndIncrement();
    value.getAndAdd(preLoaded[i]);
    if(++i >= SIZE)
      index.set(0);
  }
  @Override
  public synchronized long read() { return value.get(); }
  @Override
  public void report(Accumulator acc2) {
    printf("%-22s: %.2f\n", "synch/(Atomic-synch)",
      (double)acc2.duration/
        ((double)this.duration - (double)acc2.duration));
  }
}

public class SynchronizationComparisons {
  static SynchronizedTest synch = new SynchronizedTest();
  static LockTest lock = new LockTest();
  static AtomicTest atomic = new AtomicTest();
  static void test() {
    print("============================");
    printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
    synch.timedTest();
    lock.timedTest();
    atomic.timedTest();
    synch.report(lock);
    atomic.report(synch);
  }
  public static void main(String[] args) {
    int iterations = 5; // Default
    if(args.length > 0) // Optionally change iterations
      iterations = new Integer(args[0]);
    // The first time fills the thread pool:
    print("Warmup");
    synch.timedTest();
    // Now the initial test doesn't include the cost
    // of starting the threads for the first time.
    // Produce multiple data points:
    for(int i = 0; i < iterations; i++) {
      test();
      Accumulator.cycles *= 2;
    }
    Accumulator.exec.shutdown();
  }
} /* Output: (Sample) using JDK6u10
Warmup
synch        :     129868038
============================
Cycles       :         50000
synch        :     126407922
Lock         :      51207369
Atomic       :     141845223
synch/Lock            : 2.47
synch/(Atomic-synch)  : 8.19
============================
Cycles       :        100000
synch        :     251174061
Lock         :     105338114
Atomic       :     279503250
synch/Lock            : 2.38
synch/(Atomic-synch)  : 8.87
============================
Cycles       :        200000
synch        :     508778006
Lock         :     214398402
Atomic       :     574464795
synch/Lock            : 2.37
synch/(Atomic-synch)  : 7.75
============================
Cycles       :        400000
synch        :    1027003521
Lock         :     428342577
Atomic       :    1115667617
synch/Lock            : 2.40
synch/(Atomic-synch)  : 11.58
============================
Cycles       :        800000
synch        :    2179255097
Lock         :     877216314
Atomic       :    2371504710
synch/Lock            : 2.48
synch/(Atomic-synch)  : 11.34
*///:~
