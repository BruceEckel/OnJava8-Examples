// threads/MutexEvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Preventing thread collisions with mutexes
// {IgnoreOutput} // No output validation
import java.util.concurrent.locks.*;
import onjava.TimedAbort;

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
    new TimedAbort(4);
    EvenChecker.test(new MutexEvenSupplier());
  }
}
