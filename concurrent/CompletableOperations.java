// concurrent/CompletableOperations.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;

public class CompletableOperations {
  static CompletableFuture<Integer> cfi() {
    return
      CompletableFuture.completedFuture(
        new Integer(1));
  }
  // Get and show value stored in a CF:
  static void showr(CompletableFuture<Integer> c) {
    try {
      System.out.println(c.get());
    } catch(InterruptedException
            | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
  // For CF operations that have no value:
  static void voidr(CompletableFuture<Void> c) {
    try {
      c.get(); // Returns void
    } catch(InterruptedException
            | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
  public static void main(String[] args) {
    showr(cfi()); // Basic test
    showr(cfi().thenApplyAsync(i -> i + 42));
    voidr(cfi().runAsync(() -> System.out.println("run")));
    CompletableFuture<Integer> c = cfi();
    c.obtrudeValue(111);
    showr(c);
  }
}
