// tasks/waxomatic/WaxOMatic.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Basic task cooperation
package tasks.waxomatic;
import java.util.concurrent.*;

class Car {
  private boolean waxOn = false;
  public synchronized void waxed() {
    waxOn = true; // Ready to buff
    notifyAll();
  }
  public synchronized void buffed() {
    waxOn = false; // Ready for another coat of wax
    notifyAll();
  }
  public synchronized void waitForWaxing()
  throws InterruptedException {
    while(waxOn == false)
      wait();
  }
  public synchronized void waitForBuffing()
  throws InterruptedException {
    while(waxOn == true)
      wait();
  }
}

class WaxOn implements Runnable {
  private Car car;
  public WaxOn(Car c) { car = c; }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        System.out.print("Wax On! ");
        TimeUnit.MILLISECONDS.sleep(200);
        car.waxed();
        car.waitForBuffing();
      }
    } catch(InterruptedException e) {
      System.out.println("Exiting via interrupt");
    }
    System.out.println("Ending Wax On task");
  }
}

class WaxOff implements Runnable {
  private Car car;
  public WaxOff(Car c) { car = c; }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        car.waitForWaxing();
        System.out.print("Wax Off! ");
        TimeUnit.MILLISECONDS.sleep(200);
        car.buffed();
      }
    } catch(InterruptedException e) {
      System.out.println("Exiting via interrupt");
    }
    System.out.println("Ending Wax Off task");
  }
}

public class WaxOMatic {
  public static void
  main(String[] args) throws Exception {
    Car car = new Car();
    ExecutorService es = Executors.newCachedThreadPool();
    es.execute(new WaxOff(car));
    es.execute(new WaxOn(car));
    TimeUnit.SECONDS.sleep(5); // Run for a while...
    es.shutdownNow(); // Interrupt all tasks
  }
}
/* Output:
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On!
Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off!
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On!
Wax Off! Wax On! Wax Off! Wax On! Exiting via interrupt
Ending Wax On task
Exiting via interrupt
Ending Wax Off task
*/
