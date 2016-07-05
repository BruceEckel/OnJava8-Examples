// threads/QuittingTasks.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class QuittingTasks {
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService exec =
      Executors.newCachedThreadPool();
    List<QuittableTask> tasks =
      IntStream.range(1, 1000)
        .mapToObj(i -> new QuittableTask())
        .peek(exec::execute)
        .collect(Collectors.toList());
    TimeUnit.MILLISECONDS.sleep(100);
    tasks.forEach(QuittableTask::quit);
    exec.shutdown();
    exec.awaitTermination(1, TimeUnit.SECONDS);
    // See if any tasks are still running:
    System.out.println(
      tasks.stream()
        .anyMatch(QuittableTask::running));
  }
}
/* Output:
false
*/
