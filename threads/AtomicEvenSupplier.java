// threads/AtomicEvenSupplier.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Atomic classes are occasionally useful in regular code
// {TimeOutDuringTesting}
// {IgnoreOutput} // No output validation
import java.util.concurrent.atomic.*;

public class AtomicEvenSupplier extends IntSupplier {
  private AtomicInteger currentEvenValue =
    new AtomicInteger(0);
  @Override
  public int next() {
    return currentEvenValue.addAndGet(2);
  }
  public static void main(String[] args) {
    EvenChecker.test(new AtomicEvenSupplier());
  }
}
