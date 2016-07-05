// threads/SynchronizedEvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Simplifying mutexes with the synchronized keyword
// {TimeOutDuringTesting}
// {IgnoreOutput} // No output validation

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
