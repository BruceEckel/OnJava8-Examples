// generics/PrimitiveGenericTest.java
import com.mindviewinc.util.*;
import java.util.function.*;

// Fill an array using a generator:
class FArray {
  public static <T> T[] fill(T[] a, Supplier<T> gen) {
    for(int i = 0; i < a.length; i++)
      a[i] = gen.get();
    return a;
  }
}

public class PrimitiveGenericTest {
  public static void main(String[] args) {
    String[] strings = FArray.fill(
      new String[7], new RandomSupplier.String(10));
    for(String s : strings)
      System.out.println(s);
    Integer[] integers = FArray.fill(
      new Integer[7], new RandomSupplier.Integer());
    for(int i: integers)
      System.out.println(i);
    // Autoboxing won't save you here. This won't compile:
    // int[] b =
    //   FArray.fill(new int[7], new RandIntSupplier());
  }
}
/* Output:
YNzbrnyGcF
OWZnTcQrGs
eGZMmJMRoE
suEcUOneOE
dLsmwHLGEa
hKcxrEqUCB
bkInaMesbt
7052
6665
2654
3909
5202
2209
5458
*/
