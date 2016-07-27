// threads/AtomicityTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;
import onjava.TimedAbort;

public class AtomicityTest implements Runnable {
  private int i = 0;
  public int getValue() { return i; }
  private synchronized void evenIncrement() { i++; i++; }
  @Override
  public void run() {
    while(true)
      evenIncrement();
  }
  public static void main(String[] args) {
    new TimedAbort(4);
    ExecutorService es = Executors.newCachedThreadPool();
    AtomicityTest at = new AtomicityTest();
    es.execute(at);
    while(true) {
      int val = at.getValue();
      if(val % 2 != 0) {
        System.out.println(val);
        System.exit(0);
      }
    }
  }
}
/* Output:
1
*/
