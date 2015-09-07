// concurrency/ExplicitCriticalSection.java
// ©2015 MindView LLC: see Copyright.txt
// {ThrowsException} on a multiprocessor machine
// Using explicit Lock objects to create
// critical sections.
package concurrency;
import java.util.concurrent.locks.*;

// Synchronize the entire method:
class ExplicitPairManager1 extends PairManager {
  private Lock lock = new ReentrantLock();
  @Override
  public void increment() {
    lock.lock();
    try {
      p.incrementX();
      p.incrementY();
      store(getPair());
    } finally {
      lock.unlock();
    }
  }
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {
  private Lock lock = new ReentrantLock();
  @Override
  public void increment() {
    Pair temp;
    lock.lock();
    try {
      p.incrementX();
      p.incrementY();
      temp = getPair();
    } finally {
      lock.unlock();
    }
    store(temp);
  }
}

public class ExplicitCriticalSection {
  public static void
  main(String[] args) throws Exception {
    PairManager
      pman1 = new ExplicitPairManager1(),
      pman2 = new ExplicitPairManager2();
    CriticalSection.testApproaches(pman1, pman2);
  }
}
/* Output:
pm1: Pair: x: 10, y: 10 checkCounter = 68453
pm2: Pair: x: 11, y: 11 checkCounter = 663407
___[ Error Output ]___
Exception in thread "pool-1-thread-3"
concurrency.Pair$PairValuesNotEqualException: Pair values
not equal: x: 1, y: 0
        at
concurrency.Pair.checkState(CriticalSection.java:36)
        at
concurrency.PairChecker.run(CriticalSection.java:110)
        at
java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown
Source)
        at
java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown
Source)
        at java.lang.Thread.run(Unknown Source)
Exception in thread "pool-1-thread-4"
concurrency.Pair$PairValuesNotEqualException: Pair values
not equal: x: 2, y: 1
        at
concurrency.Pair.checkState(CriticalSection.java:36)
        at
concurrency.PairChecker.run(CriticalSection.java:110)
        at
java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown
Source)
        at
java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown
Source)
        at java.lang.Thread.run(Unknown Source)
___[ Exception is Expected ]___
*/
