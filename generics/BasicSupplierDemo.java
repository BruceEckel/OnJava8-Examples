// generics/BasicSupplierDemo.java
import com.mindviewinc.util.*;
import java.util.function.*;

public class BasicSupplierDemo {
  public static void main(String[] args) {
    Supplier<CountedObject> gen =
      BasicSupplier.create(CountedObject.class);
    for(int i = 0; i < 5; i++)
      System.out.println(gen.get());
  }
}
/* Output:
CountedObject 0
CountedObject 1
CountedObject 2
CountedObject 3
CountedObject 4
*/
