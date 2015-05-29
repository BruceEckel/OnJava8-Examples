//: containers/SimpleHashMap.java
// ©2015 MindView LLC: see Copyright.txt
// A demonstration hashed Map.
import java.util.*;
import net.mindview.util.*;

public class SimpleHashMap<K,V> extends AbstractMap<K,V> {
  // Choose a prime number for the hash table
  // size, to achieve a uniform distribution:
  static final int SIZE = 997;
  // You can't have a physical array of generics,
  // but you can upcast to one:
  @SuppressWarnings("unchecked")
  LinkedList<MapEntry<K,V>>[] buckets =
    new LinkedList[SIZE];
  @Override
  public V put(K key, V value) {
    V oldValue = null;
    int index = Math.abs(key.hashCode()) % SIZE;
    if(buckets[index] == null)
      buckets[index] = new LinkedList<>();
    LinkedList<MapEntry<K,V>> bucket = buckets[index];
    MapEntry<K,V> pair = new MapEntry<>(key, value);
    boolean found = false;
    ListIterator<MapEntry<K,V>> it = bucket.listIterator();
    while(it.hasNext()) {
      MapEntry<K,V> iPair = it.next();
      if(iPair.getKey().equals(key)) {
        oldValue = iPair.getValue();
        it.set(pair); // Replace old with new
        found = true;
        break;
      }
    }
    if(!found)
      buckets[index].add(pair);
    return oldValue;
  }
  @Override
  public V get(Object key) {
    int index = Math.abs(key.hashCode()) % SIZE;
    if(buckets[index] == null) return null;
    for(MapEntry<K,V> iPair : buckets[index])
      if(iPair.getKey().equals(key))
        return iPair.getValue();
    return null;
  }
  @Override
  public Set<Map.Entry<K,V>> entrySet() {
    Set<Map.Entry<K,V>> set= new HashSet<>();
    for(LinkedList<MapEntry<K,V>> bucket : buckets) {
      if(bucket == null) continue;
      for(MapEntry<K,V> mpair : bucket)
        set.add(mpair);
    }
    return set;
  }
  public static void main(String[] args) {
    SimpleHashMap<String,String> m = new SimpleHashMap<>();
    m.putAll(Countries.capitals(25));
    System.out.println(m);
    System.out.println(m.get("ERITREA"));
    System.out.println(m.entrySet());
  }
} /* Output:
{CHAD=N'djamena, BISSAU=Bissau, CONGO=Brazzaville, BURUNDI=Bujumbura, DJIBOUTI=Dijibouti, EQUATORIAL GUINEA=Malabo, GUINEA=Conakry, LESOTHO=Maseru, EGYPT=Cairo, GHANA=Accra, CENTRAL AFRICAN REPUBLIC=Bangui, BENIN=Porto-Novo, GABON=Libreville, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, KENYA=Nairobi, ETHIOPIA=Addis Ababa, ALGERIA=Algiers, BOTSWANA=Gaberone, COMOROS=Moroni, ANGOLA=Luanda, ERITREA=Asmara, CAPE VERDE=Praia, BURKINA FASO=Ouagadougou, THE GAMBIA=Banjul, CAMEROON=Yaounde}
Asmara
[CHAD=N'djamena, BISSAU=Bissau, CONGO=Brazzaville, BURUNDI=Bujumbura, DJIBOUTI=Dijibouti, EQUATORIAL GUINEA=Malabo, GUINEA=Conakry, LESOTHO=Maseru, EGYPT=Cairo, GHANA=Accra, CENTRAL AFRICAN REPUBLIC=Bangui, BENIN=Porto-Novo, GABON=Libreville, COTE D'IVOIR (IVORY COAST)=Yamoussoukro, KENYA=Nairobi, ETHIOPIA=Addis Ababa, ALGERIA=Algiers, BOTSWANA=Gaberone, COMOROS=Moroni, ANGOLA=Luanda, ERITREA=Asmara, CAPE VERDE=Praia, BURKINA FASO=Ouagadougou, THE GAMBIA=Banjul, CAMEROON=Yaounde]
*///:~
