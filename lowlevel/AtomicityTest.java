// lowlevel/AtomicityTest.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import onjava.TimedAbort;

public class AtomicityTest implements Runnable {
  private int i = 0;
  public int getValue() { return i; }
  private synchronized void evenIncrement() {
    i++; i++;
  }
  @Override
  public void run() {
    while(true)
      evenIncrement();
  }
  public static void main(String[] args) {
    new TimedAbort(4, "No failures found");
    AtomicityTest at = new AtomicityTest();
    CompletableFuture.runAsync(at);
    while(true) {
      int val = at.getValue();
      if(val % 2 != 0) {
        System.out.println("failed with: " + val);
        System.exit(0);
      }
    }
  }
}
/* Output:
failed with: 21
*/
