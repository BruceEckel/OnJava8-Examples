// concurrent/Workable.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.concurrent.*;
import onjava.Nap;

public class Workable {
  String id;
  final int duration;
  public Workable(String id, int duration) {
    this.id = id;
    this.duration = duration;
  }
  @Override
  public String toString() {
    return "Workable[" + id + "]";
  }
  public static Workable work(Workable tt) {
    new Nap(tt.duration); // Milliseconds
    tt.id = tt.id + "W";
    System.out.println(tt);
    return tt;
  }
  public static CompletableFuture<Workable>
  make(String id, int duration) {
    return
      CompletableFuture.completedFuture(
        new Workable(id, duration))
      .thenApplyAsync(Workable::work);
  }
}
