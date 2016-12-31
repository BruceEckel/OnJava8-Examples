// concurrent/Batter.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import onjava.Nap;

public class Batter {
  static class Eggs {}
  static class Milk {}
  static class Sugar {}
  static class Flour {}
  static <T> T prepare(T ingredient) {
    new Nap(100);
    return ingredient;
  }
  static <T> CompletableFuture<T> cf(T ingredient) {
    return CompletableFuture
      .completedFuture(ingredient)
      .thenApply(Batter::prepare);
  }
  public static CompletableFuture<Batter> mix() {
    CompletableFuture<Eggs> eggs = cf(new Eggs());
    CompletableFuture<Milk> milk = cf(new Milk());
    CompletableFuture<Sugar> sugar = cf(new Sugar());
    CompletableFuture<Flour> flour = cf(new Flour());
    CompletableFuture
      .allOf(eggs, milk, sugar, flour)
      .join();
    new Nap(100); // Mixing time
    return
      CompletableFuture.completedFuture(new Batter());
  }
}
