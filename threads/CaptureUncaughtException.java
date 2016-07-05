// threads/CaptureUncaughtException.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// {TimeOutDuringTesting}
import java.util.concurrent.*;

class ExceptionThread2 implements Runnable {
  @Override
  public void run() {
    Thread t = Thread.currentThread();
    System.out.println("run() by " + t.getName());
    System.out.println(
      "eh = " + t.getUncaughtExceptionHandler());
    throw new RuntimeException();
  }
}

class MyUncaughtExceptionHandler implements
Thread.UncaughtExceptionHandler {
  @Override
  public void uncaughtException(Thread t, Throwable e) {
    System.out.println("caught " + e);
  }
}

class HandlerThreadFactory implements ThreadFactory {
  @Override
  public Thread newThread(Runnable r) {
    System.out.println(this + " creating new Thread");
    Thread t = new Thread(r);
    System.out.println("created " + t);
    t.setUncaughtExceptionHandler(
      new MyUncaughtExceptionHandler());
    System.out.println(
      "eh = " + t.getUncaughtExceptionHandler());
    return t;
  }
}

public class CaptureUncaughtException {
  public static void main(String[] args) {
    ExecutorService exec = Executors.newCachedThreadPool(
      new HandlerThreadFactory());
    exec.execute(new ExceptionThread2());
  }
}
/* Output:
HandlerThreadFactory@10dea4e creating new Thread
created Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@647e05
run() by Thread[Thread-0,5,main]
eh = MyUncaughtExceptionHandler@647e05
HandlerThreadFactory@10dea4e creating new Thread
created Thread[Thread-1,5,main]
eh = MyUncaughtExceptionHandler@c34ec2
caught java.lang.RuntimeException
*/
