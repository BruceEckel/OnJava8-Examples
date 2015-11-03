// generics/Suppliers.java
// A utility to use with Suppliers.
import generics.coffee.*;
import java.util.*;
import java.util.function.*;

public class Suppliers {
  public static <T> Collection<T>
  fill(Collection<T> coll, Supplier<T> gen, int n) {
    for(int i = 0; i < n; i++)
      coll.add(gen.get());
    return coll;
  }
  public static void main(String[] args) {
    Collection<Coffee> coffee = fill(
      new ArrayList<>(), new CoffeeSupplier(), 4);
    for(Coffee c : coffee)
      System.out.println(c);
    Collection<Integer> fnumbers = fill(
      new ArrayList<>(), new Fibonacci(), 12);
    for(int i : fnumbers)
      System.out.print(i + ", ");
  }
}
/* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
*/
