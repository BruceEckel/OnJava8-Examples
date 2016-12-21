// concurrent/DualCompletableOperations.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

public class DualCompletableOperations {
  static
  CompletableFuture<RandomWork> makeCF(int id) {
    return
      CompletableFuture.completedFuture(
        new RandomWork(id))
      .thenApplyAsync(RandomWork::work);
  }
  static CompletableFuture<RandomWork> cfA, cfB;
  static int count = 0;
  static void init() {
    cfA = makeCF(count++);
    cfB = makeCF(count++);
  }
  static void join() {
    cfA.join();
    cfB.join();
  }
  public static void main(String[] args) {
    init();
    cfA.runAfterBothAsync(cfB, () ->
      System.out.println("Completed Both"));
    join();

    init();
    cfA.runAfterEitherAsync(cfB, () ->
      System.out.println("Completed Either"));
    join();

    init();
    cfA.applyToEitherAsync(cfB, rw -> {
      System.out.println("applyToEither: " + rw);
      return rw;
    });
    join();

  }
}
/* Output:
*/
