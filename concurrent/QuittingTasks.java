// concurrent/QuittingTasks.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class QuittingTasks {
  public static final int COUNT = 150;
  public static void main(String[] args)
    throws InterruptedException {
    ExecutorService es =
      Executors.newCachedThreadPool();
    List<QuittableTask> tasks =
      IntStream.range(1, COUNT)
        .mapToObj(QuittableTask::new)
        .peek(qt -> es.execute(qt))
        .collect(Collectors.toList());
    TimeUnit.SECONDS.sleep(1);
    tasks.forEach(QuittableTask::quit);
    es.shutdown();
  }
}
