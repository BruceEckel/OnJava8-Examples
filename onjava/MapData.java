// onjava/MapData.java
// A Map filled with data using a generator object.
package onjava;
import java.util.*;
import java.util.function.*;

public class MapData<K, V> extends LinkedHashMap<K, V> {
  // A single Pair Supplier:
  public MapData(Supplier<Pair<K, V>> gen, int quantity) {
    for(int i = 0; i < quantity; i++) {
      Pair<K, V> p = gen.get();
      put(p.key, p.value);
    }
  }
  // Two separate Suppliers:
  public MapData(Supplier<K> genK, Supplier<V> genV,
      int quantity) {
    for(int i = 0; i < quantity; i++) {
      put(genK.get(), genV.get());
    }
  }
  // A key Supplier and a single value:
  public MapData(Supplier<K> genK, V value, int quantity){
    for(int i = 0; i < quantity; i++) {
      put(genK.get(), value);
    }
  }
  // An Iterable and a value Supplier:
  public MapData(Iterable<K> genK, Supplier<V> genV) {
    for(K key : genK) {
      put(key, genV.get());
    }
  }
  // An Iterable and a single value:
  public MapData(Iterable<K> genK, V value) {
    for(K key : genK) {
      put(key, value);
    }
  }
  // Generic convenience methods:
  public static <K, V> MapData<K, V>
  map(Supplier<Pair<K, V>> gen, int quantity) {
    return new MapData<>(gen, quantity);
  }
  public static <K, V> MapData<K, V>
  map(Supplier<K> genK, Supplier<V> genV, int quantity) {
    return new MapData<>(genK, genV, quantity);
  }
  public static <K, V> MapData<K, V>
  map(Supplier<K> genK, V value, int quantity) {
    return new MapData<>(genK, value, quantity);
  }
  public static <K, V> MapData<K, V>
  map(Iterable<K> genK, Supplier<V> genV) {
    return new MapData<>(genK, genV);
  }
  public static <K, V> MapData<K, V>
  map(Iterable<K> genK, V value) {
    return new MapData<>(genK, value);
  }
}
