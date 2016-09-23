// threads/SleepAndPrintTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class SleepAndPrintTask implements Runnable {
  final int id;
  public SleepAndPrintTask(int id) { this.id = id; }
  @Override
  public void run() {
    try {
      Thread.sleep(100); // Milliseconds
    } catch(InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(this + " " +
      Thread.currentThread().getName());
  }
  @Override
  public String toString() {
    return "SleepAndPrintTask[" + id + "]";
  }
}
