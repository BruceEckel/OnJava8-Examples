// generics/TupleTest2.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import onjava.*;
import static onjava.Tuple.*;

public class TupleTest2 {
  static Tuple2<String, Integer> f() {
    return tuple("hi", 47);
  }
  static Tuple2 f2() { return tuple("hi", 47); }
  static Tuple3<Amphibian, String, Integer> g() {
    return tuple(new Amphibian(), "hi", 47);
  }
  static
  Tuple4<Vehicle, Amphibian, String, Integer> h() {
    return tuple(
      new Vehicle(), new Amphibian(), "hi", 47);
  }
  static
  Tuple5<Vehicle, Amphibian, String, Integer, Double> k() {
    return tuple(new Vehicle(), new Amphibian(),
      "hi", 47, 11.1);
  }
  public static void main(String[] args) {
    Tuple2<String, Integer> ttsi = f();
    System.out.println(ttsi);
    System.out.println(f2());
    System.out.println(g());
    System.out.println(h());
    System.out.println(k());
  }
}
/* Output:
(hi, 47)
(hi, 47)
(Amphibian@1540e19d, hi, 47)
(Vehicle@7f31245a, Amphibian@6d6f6e28, hi, 47)
(Vehicle@330bedb4, Amphibian@2503dbd3, hi, 47, 11.1)
*/
