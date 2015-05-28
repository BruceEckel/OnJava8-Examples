//: innerclasses/ArgReturnReferences.java
// Demonstrates method references
import java.util.function.*;
import static net.mindview.util.Print.*;

class Y {
  static Y create() { return new Y(); }
  static void absorb(Y y) {}
  static String transform1(Y y) { return "Y"; }
  static String transform2(Y y, String s) {
    return "Y" + " " + s;
  }
}

public class ArgReturnReferences {
  Supplier<Y> supply = Y::create;
  Consumer<Y> consume = Y::absorb;
  Function<Y, String> f1arg = Y::transform1;
  BiFunction<Y, String, String> f2arg =
    Y::transform2;
  public static void main(String[] args) {
    ArgReturnReferences arr =
      new ArgReturnReferences();
    Y y = arr.supply.get();
    arr.consume.accept(y);
    print(arr.f1arg.apply(y));
    print(arr.f2arg.apply(y, "Howdy"));
  }
} ///:~
