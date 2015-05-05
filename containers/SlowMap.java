//: containers/SlowMap.java
// A Map implemented with ArrayLists.
import java.util.*;
import net.mindview.util.*;

public class SlowMap<K,V> extends AbstractMap<K,V> {
  private List<K> keys = new ArrayList<>();
  private List<V> values = new ArrayList<>();
  @Override
  public V put(K key, V value) {
    V oldValue = get(key); // The old value or null
    if(!keys.contains(key)) {
      keys.add(key);
      values.add(value);
    } else
      values.set(keys.indexOf(key), value);
    return oldValue;
  }
  @Override
  public V get(Object key) { // key is type Object, not K
    if(!keys.contains(key))
      return null;
    return values.get(keys.indexOf(key));
  }
  @Override
  public Set<Map.Entry<K,V>> entrySet() {
    Set<Map.Entry<K,V>> set= new HashSet<>();
    Iterator<K> ki = keys.iterator();
    Iterator<V> vi = values.iterator();
    while(ki.hasNext())
      set.add(new MapEntry<>(ki.next(), vi.next()));
    return set;
  }
  public static void main(String[] args) {
    SlowMap<String,String> m= new SlowMap<>();
    m.putAll(Countries.capitals(15));
    System.out.println(m);
    System.out.println(m.get("BULGARIA"));
    System.out.println(m.entrySet());
  }
} /* Output:
{CHAD=N'djamena, ALGERIA=Algiers, BOTSWANA=Gaberone, BURUNDI=Bujumbura, CONGO=Brazzaville, DJIBOUTI=Dijibouti, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, ANGOLA=Luanda, EGYPT=Cairo, CAPE VERDE=Praia, BURKINA FASO=Ouagadougou, BENIN=Porto-Novo, CENTRAL AFRICAN REPUBLIC=Bangui, CAMEROON=Yaounde}
null
[CHAD=N'djamena, ALGERIA=Algiers, BOTSWANA=Gaberone, BURUNDI=Bujumbura, CONGO=Brazzaville, DJIBOUTI=Dijibouti, COMOROS=Moroni, EQUATORIAL GUINEA=Malabo, ANGOLA=Luanda, EGYPT=Cairo, CAPE VERDE=Praia, BURKINA FASO=Ouagadougou, BENIN=Porto-Novo, CENTRAL AFRICAN REPUBLIC=Bangui, CAMEROON=Yaounde]
*///:~
