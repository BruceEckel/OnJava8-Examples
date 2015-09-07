// concurrency/SimpleDaemons.java
// ©2015 MindView LLC: see Copyright.txt
// Daemon threads don't prevent the program from ending.
import java.util.concurrent.*;
import static com.mindviewinc.util.Print.*;

public class SimpleDaemons implements Runnable {
  @Override
  public void run() {
    try {
      while(true) {
        TimeUnit.MILLISECONDS.sleep(100);
        print(Thread.currentThread() + " " + this);
      }
    } catch(InterruptedException e) {
      print("sleep() interrupted");
    }
  }
  public static void main(String[] args) throws Exception {
    for(int i = 0; i < 10; i++) {
      Thread daemon = new Thread(new SimpleDaemons());
      daemon.setDaemon(true); // Must call before start()
      daemon.start();
    }
    print("All daemons started");
    TimeUnit.MILLISECONDS.sleep(175);
  }
}
/* Output:
All daemons started
Thread[Thread-6,5,main] SimpleDaemons@b87187
Thread[Thread-4,5,main] SimpleDaemons@1fb28c1
Thread[Thread-8,5,main] SimpleDaemons@8ebcf
Thread[Thread-7,5,main] SimpleDaemons@54761d
Thread[Thread-9,5,main] SimpleDaemons@156afef
Thread[Thread-0,5,main] SimpleDaemons@1320c4d
Thread[Thread-3,5,main] SimpleDaemons@4683f2
Thread[Thread-2,5,main] SimpleDaemons@4f80f2
Thread[Thread-5,5,main] SimpleDaemons@29c11d
Thread[Thread-1,5,main] SimpleDaemons@9df74c
*/
