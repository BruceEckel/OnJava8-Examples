// threads/QuittableTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

public class QuittableTask implements Runnable {
  private boolean running = true;
  public void quit() { running = false; }
  public boolean running() { return running; }
  @Override
  public void run() {
    while(running)
      try {
        TimeUnit.MILLISECONDS.sleep(100);
      } catch(InterruptedException e) {
        throw new RuntimeException(e);
      }
  }
}
