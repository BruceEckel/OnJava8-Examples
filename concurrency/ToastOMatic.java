// concurrency/ToastOMatic.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A toaster that uses queues.
import java.util.concurrent.*;
import java.util.*;

class Toast {
  public enum Status { DRY, BUTTERED, JAMMED }
  private Status status = Status.DRY;
  private final int id;
  public Toast(int idn) { id = idn; }
  public void butter() { status = Status.BUTTERED; }
  public void jam() { status = Status.JAMMED; }
  public Status getStatus() { return status; }
  public int getId() { return id; }
  @Override
  public String toString() {
    return "Toast " + id + ": " + status;
  }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {}

class Toaster implements Runnable {
  private ToastQueue toastQueue;
  private int count = 0;
  private Random rand = new Random(47);
  public Toaster(ToastQueue tq) { toastQueue = tq; }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        TimeUnit.MILLISECONDS.sleep(
          100 + rand.nextInt(500));
        // Make toast
        Toast t = new Toast(count++);
        System.out.println(t);
        // Insert into queue
        toastQueue.put(t);
      }
    } catch(InterruptedException e) {
      System.out.println("Toaster interrupted");
    }
    System.out.println("Toaster off");
  }
}

// Apply butter to toast:
class Butterer implements Runnable {
  private ToastQueue dryQueue, butteredQueue;
  public Butterer(ToastQueue dry, ToastQueue buttered) {
    dryQueue = dry;
    butteredQueue = buttered;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = dryQueue.take();
        t.butter();
        System.out.println(t);
        butteredQueue.put(t);
      }
    } catch(InterruptedException e) {
      System.out.println("Butterer interrupted");
    }
    System.out.println("Butterer off");
  }
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
  private ToastQueue butteredQueue, finishedQueue;
  public Jammer(ToastQueue buttered, ToastQueue finished) {
    butteredQueue = buttered;
    finishedQueue = finished;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = butteredQueue.take();
        t.jam();
        System.out.println(t);
        finishedQueue.put(t);
      }
    } catch(InterruptedException e) {
      System.out.println("Jammer interrupted");
    }
    System.out.println("Jammer off");
  }
}

// Consume the toast:
class Eater implements Runnable {
  private ToastQueue finishedQueue;
  private int counter = 0;
  public Eater(ToastQueue finished) {
    finishedQueue = finished;
  }
  @Override
  public void run() {
    try {
      while(!Thread.interrupted()) {
        // Blocks until next piece of toast is available:
        Toast t = finishedQueue.take();
        // Verify that the toast is coming in order,
        // and that all pieces are getting jammed:
        if(t.getId() != counter++ ||
           t.getStatus() != Toast.Status.JAMMED) {
          System.out.println(">>>> Error: " + t);
          System.exit(1);
        } else
          System.out.println("Chomp! " + t);
      }
    } catch(InterruptedException e) {
      System.out.println("Eater interrupted");
    }
    System.out.println("Eater off");
  }
}

public class ToastOMatic {
  public static void main(String[] args) throws Exception {
    ToastQueue dryQueue = new ToastQueue(),
               butteredQueue = new ToastQueue(),
               finishedQueue = new ToastQueue();
    ExecutorService exec = Executors.newCachedThreadPool();
    exec.execute(new Toaster(dryQueue));
    exec.execute(new Butterer(dryQueue, butteredQueue));
    exec.execute(new Jammer(butteredQueue, finishedQueue));
    exec.execute(new Eater(finishedQueue));
    TimeUnit.SECONDS.sleep(5);
    exec.shutdownNow();
  }
}
/* Output:
Toast 0: DRY
Toast 0: BUTTERED
Toast 0: JAMMED
Chomp! Toast 0: JAMMED
Toast 1: DRY
Toast 1: BUTTERED
Toast 1: JAMMED
Chomp! Toast 1: JAMMED
Toast 2: DRY
Toast 2: BUTTERED
Toast 2: JAMMED
Chomp! Toast 2: JAMMED
Toast 3: DRY
Toast 3: BUTTERED
Toast 3: JAMMED
Chomp! Toast 3: JAMMED
Toast 4: DRY
Toast 4: BUTTERED
Toast 4: JAMMED
Chomp! Toast 4: JAMMED
Toast 5: DRY
Toast 5: BUTTERED
Toast 5: JAMMED
Chomp! Toast 5: JAMMED
Toast 6: DRY
Toast 6: BUTTERED
Toast 6: JAMMED
Chomp! Toast 6: JAMMED
Toast 7: DRY
Toast 7: BUTTERED
Toast 7: JAMMED
Chomp! Toast 7: JAMMED
Toast 8: DRY
Toast 8: BUTTERED
Toast 8: JAMMED
Chomp! Toast 8: JAMMED
Toast 9: DRY
Toast 9: BUTTERED
Toast 9: JAMMED
Chomp! Toast 9: JAMMED
Toast 10: DRY
Toast 10: BUTTERED
Toast 10: JAMMED
Chomp! Toast 10: JAMMED
Toast 11: DRY
Toast 11: BUTTERED
Toast 11: JAMMED
Chomp! Toast 11: JAMMED
Toast 12: DRY
Toast 12: BUTTERED
Toast 12: JAMMED
Chomp! Toast 12: JAMMED
Toast 13: DRY
Toast 13: BUTTERED
Toast 13: JAMMED
Chomp! Toast 13: JAMMED
Toast 14: DRY
Toast 14: BUTTERED
Toast 14: JAMMED
Chomp! Toast 14: JAMMED
Jammer interrupted
Jammer off
Butterer interrupted
Butterer off
Toaster interrupted
Eater interrupted
Toaster off
Eater off
*/
