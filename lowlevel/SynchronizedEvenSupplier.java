// lowlevel/SynchronizedEvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Simplifying mutexes with the synchronized keyword
// {IgnoreOutput} // No output validation
import onjava.TimedAbort;

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
    new TimedAbort(4);
    EvenChecker.test(new SynchronizedEvenSupplier());
  }
}
