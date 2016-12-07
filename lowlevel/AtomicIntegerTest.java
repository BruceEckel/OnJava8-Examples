// lowlevel/AtomicIntegerTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.*;
import onjava.*;

public class AtomicIntegerTest implements Runnable {
  private AtomicInteger i = new AtomicInteger(0);
  public int getValue() { return i.get(); }
  private void evenIncrement() { i.addAndGet(2); }
  @Override
  public void run() {
    while(true)
      evenIncrement();
  }
  public static void main(String[] args) {
    new TimedAbort(5); // Terminate after 5 seconds
    ExecutorService exec =
      Executors.newCachedThreadPool();
    AtomicIntegerTest ait = new AtomicIntegerTest();
    exec.execute(ait);
    while(true) {
      int val = ait.getValue();
      if(val % 2 != 0) {
        System.out.println(val);
        System.exit(0);
      }
    }
  }
}
/* Output:
TimedAbort 5
*/
