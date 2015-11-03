// concurrency/MutexEvenSupplier.java
// Preventing thread collisions with mutexes.
// {TimeOutDuringTesting}
import java.util.concurrent.locks.*;

public class MutexEvenSupplier extends IntSupplier {
  private int currentEvenValue = 0;
  private Lock lock = new ReentrantLock();
  @Override
  public int next() {
    lock.lock();
    try {
      ++currentEvenValue;
      Thread.yield(); // Cause failure faster
      ++currentEvenValue;
      return currentEvenValue;
    } finally {
      lock.unlock();
    }
  }
  public static void main(String[] args) {
    EvenChecker.test(new MutexEvenSupplier());
  }
}
/* Output: (None) */
