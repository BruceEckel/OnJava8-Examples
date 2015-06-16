//: generics/TupleTest2.java
// ©2015 MindView LLC: see Copyright.txt
import com.mindviewinc.util.*;
import static com.mindviewinc.util.Tuple.*;

public class TupleTest2 {
  static TwoTuple<String,Integer> f() {
    return tuple("hi", 47);
  }
  static TwoTuple f2() { return tuple("hi", 47); }
  static ThreeTuple<Amphibian,String,Integer> g() {
    return tuple(new Amphibian(), "hi", 47);
  }
  static
  FourTuple<Vehicle,Amphibian,String,Integer> h() {
    return tuple(new Vehicle(), new Amphibian(), "hi", 47);
  }
  static
  FiveTuple<Vehicle,Amphibian,String,Integer,Double> k() {
    return tuple(new Vehicle(), new Amphibian(),
      "hi", 47, 11.1);
  }
  public static void main(String[] args) {
    TwoTuple<String,Integer> ttsi = f();
    System.out.println(ttsi);
    System.out.println(f2());
    System.out.println(g());
    System.out.println(h());
    System.out.println(k());
  }
} /* Output:
(hi, 47)
(hi, 47)
(Amphibian@139a55, hi, 47)
(Vehicle@1db9742, Amphibian@106d69c, hi, 47)
(Vehicle@52e922, Amphibian@25154f, hi, 47, 11.1)
*///:~
