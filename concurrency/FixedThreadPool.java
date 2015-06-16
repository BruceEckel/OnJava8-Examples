//: concurrency/FixedThreadPool.java
// ©2015 MindView LLC: see Copyright.txt
import java.util.concurrent.*;

public class FixedThreadPool {
  public static void main(String[] args) {
    // Constructor argument is number of threads:
    ExecutorService exec = Executors.newFixedThreadPool(5);
    for(int i = 0; i < 5; i++)
      exec.execute(new LiftOff());
    exec.shutdown();
  }
} /* Output:
#3(9), #2(9), #3(8), #2(8), #1(9), #4(9), #0(9), #2(7),
#3(7), #1(8), #0(8), #4(8), #3(6), #2(6), #0(7), #1(7),
#0(6), #2(5), #4(7), #3(5), #0(5), #1(6), #0(4), #4(6),
#3(4), #2(4), #1(5), #4(5), #0(3), #2(3), #3(3), #2(2),
#0(2), #4(4), #1(4), #0(1), #2(1), #3(2), #0(Liftoff!),
#1(3), #4(3), #1(2), #3(1), #2(Liftoff!), #3(Liftoff!),
#1(1), #4(2), #1(Liftoff!), #4(1), #4(Liftoff!),
*///:~
