// containersindepth/MapEntry.java
// ©2016 MindView LLC: see Copyright.txt
// A simple Map.Entry for sample Map implementations.
import java.util.*;

public class MapEntry<K, V> implements Map.Entry<K, V> {
  private K key;
  private V value;
  public MapEntry(K key, V value) {
    this.key = key;
    this.value = value;
  }
  @Override
  public K getKey() { return key; }
  @Override
  public V getValue() { return value; }
  @Override
  public V setValue(V v) {
    V result = value;
    value = v;
    return result;
  }
  @Override
  public int hashCode() {
    return (key==null ? 0 : key.hashCode()) ^
      (value==null ? 0 : value.hashCode());
  }
  @Override
  public boolean equals(Object o) {
    if(!(o instanceof MapEntry)) return false;
    @SuppressWarnings("unchecked")
    MapEntry<K, V> me = (MapEntry<K, V>)o;
    return
      (key == null ?
       me.getKey() == null : key.equals(me.getKey())) &&
      (value == null ?
       me.getValue()== null : value.equals(me.getValue()));
  }
  @Override
  public String toString() { return key + "=" + value; }
}
