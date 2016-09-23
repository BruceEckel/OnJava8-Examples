// generics/TupleTest.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import onjava.*;

public class TupleTest {
  static Tuple2<String, Integer> f() {
    // Autoboxing converts the int to Integer:
    return new Tuple2<>("hi", 47);
  }
  static Tuple3<Amphibian, String, Integer> g() {
    return new Tuple3<>(new Amphibian(), "hi", 47);
  }
  static
  Tuple4<Vehicle, Amphibian, String, Integer> h() {
    return
      new Tuple4<>(
        new Vehicle(), new Amphibian(), "hi", 47);
  }
  static
  Tuple5<Vehicle, Amphibian, String, Integer, Double> k() {
    return new
      Tuple5<>(
        new Vehicle(), new Amphibian(), "hi", 47, 11.1);
  }
  public static void main(String[] args) {
    Tuple2<String, Integer> ttsi = f();
    System.out.println(ttsi);
    // ttsi._1 = "there"; // Compile error: final
    System.out.println(g());
    System.out.println(h());
    System.out.println(k());
  }
}
/* Output:
(hi, 47)
(Amphibian@1c7c054, hi, 47)
(Vehicle@14991ad, Amphibian@d93b30, hi, 47)
(Vehicle@a14482, Amphibian@140e19d, hi, 47, 11.1)
*/
