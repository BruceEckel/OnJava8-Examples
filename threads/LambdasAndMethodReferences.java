// threads/LambdasAndMethodReferences.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

class NotRunnable {
  public void go() {
    System.out.println("NotRunnable");
  }
}

class NotCallable {
  public Integer get() {
    System.out.println("NotCallable");
    return 1;
  }
}

public class LambdasAndMethodReferences {
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    exec.submit(() -> System.out.println("Lambda1"));
    exec.submit(new NotRunnable()::go);
    exec.submit(() -> {
      System.out.println("Lambda2");
      return 1;
    });
    exec.submit(new NotCallable()::get);
    exec.shutdown();
    exec.awaitTermination(1, TimeUnit.SECONDS);
  }
}
/* Output:
Lambda1
NotRunnable
Lambda2
NotCallable
*/
