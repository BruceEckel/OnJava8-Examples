// concurrency/DaemonsDoRunFinally.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Daemon threads now run the finally clause
import java.util.concurrent.*;

class ADaemon implements Runnable {
  @Override
  public void run() {
    try {
      System.out.println("Starting ADaemon");
      TimeUnit.SECONDS.sleep(1);
    } catch(InterruptedException e) {
      System.out.println(
        "Exiting via InterruptedException");
    } finally {
      System.out.println("ADaemon finally clause");
    }
  }
}

public class DaemonsDoRunFinally {
  public static void main(String[] args) throws Exception {
    Thread t = new Thread(new ADaemon());
    t.setDaemon(true);
    t.start();
    TimeUnit.SECONDS.sleep(2);
  }
}
/* Output:
Starting ADaemon
ADaemon finally clause
*/
