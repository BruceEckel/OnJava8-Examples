// arrays/ModifyExisting.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import onjava.*;
import static onjava.ArrayShow.*;

public class ModifyExisting {
  public static void main(String[] args) {
    double[] da = new double[7];
    Arrays.setAll(da, new Rand.Double()::get);
    show(da);
    Arrays.setAll(da, n -> da[n] / 100); // (1)
    show(da);
  }
}
/* Output:
[0.73, 0.53, 0.16, 0.19, 0.52, 0.27, 0.26]
[0.0073, 0.0053, 0.0016, 0.0019, 0.0052, 0.0027, 0.0026]
*/
