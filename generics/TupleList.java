// generics/TupleList.java
// ©2016 MindView LLC: see Copyright.txt
// Combining generic types to make complex generic types.
import java.util.*;
import onjava.*;

public class TupleList<A, B, C, D>
extends ArrayList<Tuple4<A, B, C, D>> {
  public static void main(String[] args) {
    TupleList<Vehicle, Amphibian, String, Integer> tl =
      new TupleList<>();
    tl.add(TupleTest.h());
    tl.add(TupleTest.h());
    for(Tuple4<Vehicle,Amphibian,String,Integer> i: tl)
      System.out.println(i);
  }
}
/* Output:
(Vehicle@139a55, Amphibian@1db9742, hi, 47)
(Vehicle@106d69c, Amphibian@52e922, hi, 47)
*/
