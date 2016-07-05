// threads/InterferingTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

public class InterferingTask implements Runnable {
  final int id;
  private static Integer val = new Integer(0);
  public InterferingTask(int id) { this.id = id; }
  @Override
  public void run() {
    for(int i = 0; i < 100; i++)
      val++;
    System.out.println(id + " " +
      Thread.currentThread().getName() + " " + val);
  }
}
