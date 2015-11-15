// onjava/Pair.java
// ©2016 MindView LLC: see Copyright.txt
package onjava;

public class Pair<K, V> {
  public final K key;
  public final V value;
  public Pair(K k, V v) {
    key = k;
    value = v;
  }
}
