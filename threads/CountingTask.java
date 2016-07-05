// threads/CountingTask.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.concurrent.*;

public class CountingTask implements Callable<Integer> {
  final int id;
  public CountingTask(int id) { this.id = id; }
  @Override
  public Integer call() {
    Integer val = new Integer(0);
    for(int i = 0; i < 100; i++)
      val++;
    System.out.println(id + " " +
      Thread.currentThread().getName() + " " + val);
    return val;
  }
}
