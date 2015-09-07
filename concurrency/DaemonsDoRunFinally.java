// concurrency/DaemonsDoRunFinally.java
// ©2015 MindView LLC: see Copyright.txt
// Daemon threads now run the finally clause
import java.util.concurrent.*;
import static com.mindviewinc.util.Print.*;

class ADaemon implements Runnable {
  @Override
  public void run() {
    try {
      print("Starting ADaemon");
      TimeUnit.SECONDS.sleep(1);
    } catch(InterruptedException e) {
      print("Exiting via InterruptedException");
    } finally {
      print("ADaemon finally clause");
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
