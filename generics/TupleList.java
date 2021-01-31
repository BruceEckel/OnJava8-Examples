// generics/TupleList.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Combining generic types to make complex generic types
import java.util.*;
import onjava.*;
import java.util.stream.*;

public class TupleList<A, B, C, D>
extends ArrayList<Tuple4<A, B, C, D>> {
  public static void main(String[] args) {
    TupleList<Vehicle, Amphibian, String, Integer> tl =
      new TupleList<>();
    tl.add(TupleTest2.h());
    tl.add(TupleTest2.h());
    tl.forEach(System.out::println);
  }
}
/* Output:
(Vehicle@ec7777, Amphibian@107d329, hi, 47)
(Vehicle@1629346, Amphibian@4b9385, hi, 47)
*/
