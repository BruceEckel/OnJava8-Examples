//: patterns/dynatrash/DynaTrash.java
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
} ///:~
