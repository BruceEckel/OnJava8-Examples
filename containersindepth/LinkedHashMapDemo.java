// containersindepth/LinkedHashMapDemo.java
// What you can do with a LinkedHashMap.
import java.util.*;
import com.mindviewinc.util.*;

public class LinkedHashMapDemo {
  public static void main(String[] args) {
    LinkedHashMap<Integer,String> linkedMap =
      new LinkedHashMap<>(new CountingMapData(9));
    System.out.println(linkedMap);
    // Least-recently-used order:
    linkedMap = new LinkedHashMap<>(16, 0.75f, true);
    linkedMap.putAll(new CountingMapData(9));
    System.out.println(linkedMap);
    for(int i = 0; i < 6; i++) // Cause accesses:
      linkedMap.get(i);
    System.out.println(linkedMap);
    linkedMap.get(0);
    System.out.println(linkedMap);
  }
}
/* Output:
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0}
{6=G0, 7=H0, 8=I0, 0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0}
{6=G0, 7=H0, 8=I0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 0=A0}
*/
