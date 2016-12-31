// lowlevel/QuittableTask.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import onjava.Nap;

public class QuittableTask implements Runnable {
  private boolean running = true;
  public void quit() { running = false; }
  public boolean running() { return running; }
  @Override
  public void run() {
    while(running)
      new Nap(100);
  }
}
