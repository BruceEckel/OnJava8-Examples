// concurrency/SynchronizedEvenSupplier.java
// Simplifying mutexes with the synchronized keyword.
// {TimeOutDuringTesting}

public class
SynchronizedEvenSupplier extends IntSupplier {
  private int currentEvenValue = 0;
  @Override
  public synchronized int next() {
    ++currentEvenValue;
    Thread.yield(); // Cause failure faster
    ++currentEvenValue;
    return currentEvenValue;
  }
  public static void main(String[] args) {
    EvenChecker.test(new SynchronizedEvenSupplier());
  }
}
/* Output: (None) */
