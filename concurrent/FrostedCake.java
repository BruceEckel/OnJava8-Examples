// concurrent/FrostedCake.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import onjava.Nap;

class Frosting {
  private Frosting() {}
  static CompletableFuture<Frosting> make() {
    new Nap(100);
    return CompletableFuture
      .completedFuture(new Frosting());
  }
}

public class FrostedCake {
  public FrostedCake(Baked baked, Frosting frosting) {
    new Nap(100);
  }
  public static void main(String[] args) {
    Baked.batch().forEach(baked -> baked
      .thenCombineAsync(Frosting.make(),
        (cake, frosting) ->
          new FrostedCake(cake, frosting))
      .thenAcceptAsync(System.out::println).join());
      // Need to rewrite...
  }
}
