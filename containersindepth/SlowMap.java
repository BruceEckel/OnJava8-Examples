// containersindepth/SlowMap.java
// ©2016 MindView LLC: see Copyright.txt
// A Map implemented with ArrayLists.
import java.util.*;
import onjava.*;

public class SlowMap<K, V> extends AbstractMap<K, V> {
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
  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> set= new HashSet<>();
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
}
/* Output:
{ANGOLA=Luanda, CAPE VERDE=Praia, EGYPT=Cairo,
BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers,
CAMEROON=Yaounde, CONGO=Brazzaville, CENTRAL AFRICAN
REPUBLIC=Bangui, EQUATORIAL GUINEA=Malabo, COMOROS=Moroni,
DJIBOUTI=Dijibouti, BURKINA FASO=Ouagadougou,
CHAD=N'djamena, BOTSWANA=Gaberone}
null
[ANGOLA=Luanda, CAPE VERDE=Praia, EGYPT=Cairo,
BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers,
CAMEROON=Yaounde, CONGO=Brazzaville, CENTRAL AFRICAN
REPUBLIC=Bangui, EQUATORIAL GUINEA=Malabo, COMOROS=Moroni,
DJIBOUTI=Dijibouti, BURKINA FASO=Ouagadougou,
CHAD=N'djamena, BOTSWANA=Gaberone]
*/
