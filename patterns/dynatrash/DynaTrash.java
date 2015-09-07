// patterns/dynatrash/DynaTrash.java
// ©2015 MindView LLC: see Copyright.txt
// Using a Map of Lists and RTTI
// to automatically sort trash into
// vectors. This solution, despite the
// use of RTTI, is extensible.
package patterns.dynatrash;
import patterns.trash.*;
import java.util.*;

// Generic TypeMap works in any situation:
class TypeMap<T> {
  private Map<Class,List<T>> t = new HashMap<>();
  public void add(T o) {
    Class type = o.getClass();
    if(t.containsKey(type))
      t.get(type).add(o);
    else {
      List<T> v = new ArrayList<>();
      v.add(o);
      t.put(type,v);
    }
  }
  public List<T> get(Class type) {
    return t.get(type);
  }
  public Iterator<Class> keys() {
    return t.keySet().iterator();
  }
}

// Adapter class to allow
// callbacks from ParseTrash.fillBin():
class TypeMapAdapter implements Fillable {
  TypeMap<Trash> map;
  public TypeMapAdapter(TypeMap<Trash> tm) {
    map = tm;
  }
  @Override
  public void addTrash(Trash t) { map.add(t); }
}

public class DynaTrash {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    TypeMap<Trash> bin = new TypeMap<>();
    ParseTrash.fillBin("Trash.dat",
      new TypeMapAdapter(bin));
    Iterator<Class> keys = bin.keys();
    while(keys.hasNext())
      Trash.sumValue(bin.get(keys.next()));
  }
}
/* Output: (First and last 10 Lines)
Loading patterns.trash.Glass
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Cardboard
weight of patterns.trash.Glass = 54.0
weight of patterns.trash.Glass = 17.0
weight of patterns.trash.Glass = 11.0
weight of patterns.trash.Glass = 68.0
weight of patterns.trash.Glass = 43.0
weight of patterns.trash.Glass = 63.0
________...________...________...________...________
weight of patterns.trash.Aluminum = 93.0
weight of patterns.trash.Aluminum = 36.0
Total value = 860.0499778985977
weight of patterns.trash.Paper = 22.0
weight of patterns.trash.Paper = 11.0
weight of patterns.trash.Paper = 88.0
weight of patterns.trash.Paper = 91.0
weight of patterns.trash.Paper = 80.0
weight of patterns.trash.Paper = 66.0
Total value = 35.80000053346157
*/
