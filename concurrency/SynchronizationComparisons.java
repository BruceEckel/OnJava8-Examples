//: concurrency/SynchronizationComparisons.java
// ©2015 MindView LLC: see Copyright.txt
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
      } catch(InterruptedException |
              BrokenBarrierException e) {
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
      } catch(InterruptedException |
              BrokenBarrierException e) {
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
    } catch(InterruptedException |
            BrokenBarrierException e) {
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
} /* Output: (Sample)
Warmup
synch        :      94513807
============================
Cycles       :         50000
synch        :      94514234
Lock         :      24976352
Atomic       :      98296651
synch/Lock            : 3.78
synch/(Atomic-synch)  : 24.99
============================
Cycles       :        100000
synch        :     178531353
Lock         :      46007787
Atomic       :     192372561
synch/Lock            : 3.88
synch/(Atomic-synch)  : 12.90
============================
Cycles       :        200000
synch        :     377107591
Lock         :      91051260
Atomic       :     394509274
synch/Lock            : 4.14
synch/(Atomic-synch)  : 21.67
============================
Cycles       :        400000
synch        :     722152518
Lock         :     184968090
Atomic       :     746950974
synch/Lock            : 3.90
synch/(Atomic-synch)  : 29.12
============================
Cycles       :        800000
synch        :    1478348925
Lock         :     416729956
Atomic       :    1559738238
synch/Lock            : 3.55
synch/(Atomic-synch)  : 18.16
*///:~
