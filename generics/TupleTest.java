// generics/TupleTest.java
// ©2015 MindView LLC: see Copyright.txt
import com.mindviewinc.util.*;

class Amphibian {}
class Vehicle {}

public class TupleTest {
  static TwoTuple<String, Integer> f() {
    // Autoboxing converts the int to Integer:
    return new TwoTuple<>("hi", 47);
  }
  static ThreeTuple<Amphibian, String, Integer> g() {
    return new ThreeTuple<>(new Amphibian(), "hi", 47);
  }
  static
  FourTuple<Vehicle, Amphibian, String, Integer> h() {
    return
      new FourTuple<>(
        new Vehicle(), new Amphibian(), "hi", 47);
  }
  static
  FiveTuple<Vehicle, Amphibian, String, Integer, Double> k(){
    return new
      FiveTuple<>(
        new Vehicle(), new Amphibian(), "hi", 47, 11.1);
  }
  public static void main(String[] args) {
    TwoTuple<String, Integer> ttsi = f();
    System.out.println(ttsi);
    // ttsi.first = "there"; // Compile error: final
    System.out.println(g());
    System.out.println(h());
    System.out.println(k());
  }
}
/* Output:
(hi, 47)
(Amphibian@139a55, hi, 47)
(Vehicle@1db9742, Amphibian@106d69c, hi, 47)
(Vehicle@52e922, Amphibian@25154f, hi, 47, 11.1)
*/
