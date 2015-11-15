// concurrency/CachedThreadPool.java
// ©2016 MindView LLC: see Copyright.txt
import java.util.concurrent.*;

public class CachedThreadPool {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool();
    for(int i = 0; i < 5; i++)
      exec.execute(new LiftOff());
    exec.shutdown();
  }
}
/* Output:
#0(9), #4(9), #3(9), #1(9), #2(9), #1(8), #3(8), #4(8),
#0(8), #4(7), #3(7), #1(7), #2(8), #3(6), #4(6), #0(7),
#4(5), #3(5), #1(6), #2(7), #1(5), #3(4), #4(4), #0(6),
#4(3), #3(3), #1(4), #2(6), #4(2), #0(5), #1(3), #0(4),
#4(1), #0(3), #1(2), #2(5), #3(2), #2(4), #1(1), #0(2),
#4(Liftoff!), #0(1), #1(Liftoff!), #2(3), #3(1), #2(2),
#0(Liftoff!), #2(1), #3(Liftoff!), #2(Liftoff!),
*/
