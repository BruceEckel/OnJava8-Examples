//: concurrency/DaemonFromFactory.java
// ©2015 MindView LLC: see Copyright.txt
// Using a Thread Factory to create daemons.
import java.util.concurrent.*;
import com.mindviewinc.util.*;
import static com.mindviewinc.util.Print.*;

public class DaemonFromFactory implements Runnable {
  @Override
  public void run() {
    try {
      while(true) {
        TimeUnit.MILLISECONDS.sleep(100);
        print(Thread.currentThread() + " " + this);
      }
    } catch(InterruptedException e) {
      print("Interrupted");
    }
  }
  public static void main(String[] args) throws Exception {
    ExecutorService exec = Executors.newCachedThreadPool(
      new DaemonThreadFactory());
    for(int i = 0; i < 10; i++)
      exec.execute(new DaemonFromFactory());
    print("All daemons started");
    TimeUnit.MILLISECONDS.sleep(500); // Run for a while
  }
} /* Output: (First 10 Lines)
All daemons started
Thread[Thread-7,5,main] DaemonFromFactory@e6b01c
Thread[Thread-5,5,main] DaemonFromFactory@58daee
Thread[Thread-8,5,main] DaemonFromFactory@196b6d3
Thread[Thread-4,5,main] DaemonFromFactory@122e705
Thread[Thread-9,5,main] DaemonFromFactory@15db0c6
Thread[Thread-1,5,main] DaemonFromFactory@103bc45
Thread[Thread-0,5,main] DaemonFromFactory@1e19df
Thread[Thread-3,5,main] DaemonFromFactory@963672
Thread[Thread-6,5,main] DaemonFromFactory@2f2043
                  ...
*///:~
