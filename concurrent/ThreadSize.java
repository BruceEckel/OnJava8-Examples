// concurrent/ThreadSize.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {ValidateByHand}
import java.util.concurrent.*;

public class ThreadSize {
  static class Dummy extends Thread {
    @Override
    public void run() {
      try {
        Thread.sleep(1000);
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
  public static void main(String[] args) {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    int count = 0;
    try {
      while(true) {
        exec.execute(new Dummy());
        count++;
      }
    } catch(Error e) {
      System.out.println(
        e.getClass().getSimpleName() + ": " + count);
      System.exit(0);
    }
  }
}
