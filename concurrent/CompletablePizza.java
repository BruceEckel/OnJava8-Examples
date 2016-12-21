// concurrent/CompletablePizza.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import onjava.Timer;

public class CompletablePizza {
  static int QUANTITY = 5;
  public static CompletableFuture<Pizza>
  makeCF(Pizza za) {
    return CompletableFuture
      .completedFuture(za)
      .thenApplyAsync(Pizza::roll)
      .thenApplyAsync(Pizza::sauce)
      .thenApplyAsync(Pizza::cheese)
      .thenApplyAsync(Pizza::toppings)
      .thenApplyAsync(Pizza::bake)
      .thenApplyAsync(Pizza::slice)
      .thenApplyAsync(Pizza::box);
  }
  public static void
  show(CompletableFuture<Pizza> cf) {
    try {
      System.out.println(cf.get());
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
  public static void main(String[] args) {
    Timer timer = new Timer();
    List<CompletableFuture<Pizza>> pizzas =
      IntStream.range(0, QUANTITY)
        .mapToObj(Pizza::new)
        .map(CompletablePizza::makeCF)
        .collect(Collectors.toList());
    System.out.println(timer.duration());
    pizzas.forEach(CompletablePizza::show);
    System.out.println(timer.duration());
  }
}
/* Output:
*/
