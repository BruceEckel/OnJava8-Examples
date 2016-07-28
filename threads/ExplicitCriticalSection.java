// threads/ExplicitCriticalSection.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {ThrowsException} on a multiprocessor machine
// Using explicit Lock objects to create critical sections
// {java threads.ExplicitCriticalSection}
package threads;
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
pm1: Pair: x: 10, y: 10 checkCounter = 1713716
pm2: Pair: x: 10, y: 10 checkCounter = 1717747
___[ Error Output ]___
Exception in thread "pool-1-thread-4" Exception in thread
"pool-1-thread-3" threads.Pair$PairValuesNotEqualException:
Pair values not equal: x: 2, y: 1
        at threads.Pair.checkState(CriticalSection.java:37)
        at
threads.PairChecker.run(CriticalSection.java:111)
        at java.util.concurrent.ThreadPoolExecutor.runWorke
r(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.r
un(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
threads.Pair$PairValuesNotEqualException: Pair values not
equal: x: 2, y: 1
        at threads.Pair.checkState(CriticalSection.java:37)
        at
threads.PairChecker.run(CriticalSection.java:111)
        at java.util.concurrent.ThreadPoolExecutor.runWorke
r(ThreadPoolExecutor.java:1142)
        at java.util.concurrent.ThreadPoolExecutor$Worker.r
un(ThreadPoolExecutor.java:617)
        at java.lang.Thread.run(Thread.java:745)
*/
