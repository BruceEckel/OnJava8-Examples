// onjava/FilledMap.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Fill a Map with data using a generator object
package onjava;
import java.util.*;
import java.util.function.*;

public class FilledMap<K, V> extends LinkedHashMap<K, V> {
  // A single Pair Supplier:
  public FilledMap(Supplier<Pair<K, V>> gen, int quantity) {
    for(int i = 0; i < quantity; i++) {
      Pair<K, V> p = gen.get();
      put(p.key, p.value);
    }
  }
  // Two separate Suppliers:
  public FilledMap(Supplier<K> genK, Supplier<V> genV,
      int quantity) {
    for(int i = 0; i < quantity; i++) {
      put(genK.get(), genV.get());
    }
  }
  // A key Supplier and a single value:
  public
  FilledMap(Supplier<K> genK, V value, int quantity) {
    for(int i = 0; i < quantity; i++) {
      put(genK.get(), value);
    }
  }
  // An Iterable and a value Supplier:
  public FilledMap(Iterable<K> genK, Supplier<V> genV) {
    for(K key : genK) {
      put(key, genV.get());
    }
  }
  // An Iterable and a single value:
  public FilledMap(Iterable<K> genK, V value) {
    for(K key : genK) {
      put(key, value);
    }
  }
  // Generic convenience methods:
  public static <K, V> FilledMap<K, V>
  map(Supplier<Pair<K, V>> gen, int quantity) {
    return new FilledMap<>(gen, quantity);
  }
  public static <K, V> FilledMap<K, V>
  map(Supplier<K> genK, Supplier<V> genV, int quantity) {
    return new FilledMap<>(genK, genV, quantity);
  }
  public static <K, V> FilledMap<K, V>
  map(Supplier<K> genK, V value, int quantity) {
    return new FilledMap<>(genK, value, quantity);
  }
  public static <K, V> FilledMap<K, V>
  map(Iterable<K> genK, Supplier<V> genV) {
    return new FilledMap<>(genK, genV);
  }
  public static <K, V> FilledMap<K, V>
  map(Iterable<K> genK, V value) {
    return new FilledMap<>(genK, value);
  }
}
