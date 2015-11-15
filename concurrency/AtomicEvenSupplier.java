// concurrency/AtomicEvenSupplier.java
// ©2016 MindView LLC: see Copyright.txt
// Atomic classes are occasionally useful in regular code.
// {TimeOutDuringTesting}
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
/* Output: (None) */
