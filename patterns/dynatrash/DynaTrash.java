//: patterns/dynatrash/DynaTrash.java 
// Using a HashMap of ArrayLists and RTTI
// to automatically sort trash into
// vectors. This solution, despite the
// use of RTTI, is extensible.
package patterns.dynatrash;
import patterns.trash.*;
import java.util.*;

// Generic TypeMap works in any situation:
class TypeMap {
  private HashMap t = new HashMap();
  public void add(Object o) {
    Class type = o.getClass();
    if(t.containsKey(type))
      ((ArrayList)t.get(type)).add(o);
    else {
      ArrayList v = new ArrayList();
      v.add(o);
      t.put(type,v);
    }
  }
  public ArrayList get(Class type) {
    return (ArrayList)t.get(type);
  }
  public Iterator keys() { 
    return t.keySet().iterator(); 
  }
}

// Adapter class to allow
// callbacks from ParseTrash.fillBin():
class TypeMapAdapter implements Fillable {
  TypeMap map;
  public TypeMapAdapter(TypeMap tm) { map = tm; }
  @Override
  public void addTrash(Trash t) { map.add(t); }
}

public class DynaTrash {
  public static void main(String[] args) {
    TypeMap bin = new TypeMap();
    ParseTrash.fillBin("Trash.dat", 
      new TypeMapAdapter(bin));
    Iterator keys = bin.keys();
    while(keys.hasNext())
      Trash.sumValue(
        bin.get((Class)keys.next()));
  }
} ///:~
