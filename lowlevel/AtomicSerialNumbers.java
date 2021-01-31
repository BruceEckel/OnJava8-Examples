// lowlevel/AtomicSerialNumbers.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.atomic.*;

public class
AtomicSerialNumbers extends SerialNumbers {
  private AtomicInteger serialNumber =
    new AtomicInteger();
  @Override public int nextSerialNumber() {
    return serialNumber.getAndIncrement();
  }
  public static void main(String[] args) {
    SerialNumberChecker.test(
      new AtomicSerialNumbers());
  }
}
/* Output:
No duplicates detected
*/
