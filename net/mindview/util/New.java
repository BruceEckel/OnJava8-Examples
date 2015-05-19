//: net/mindview/util/New.java
// Utilities to simplify generic container creation
// by using type argument inference.
package net.mindview.util;
import java.util.*;

public class New {
  public static <K,V> Map<K,V> map() {
    return new HashMap<>();
  }
  public static <T> List<T> list() {
    return new ArrayList<>();
  }
  public static <T> LinkedList<T> lList() {
    return new LinkedList<>();
  }
  public static <T> Set<T> set() {
    return new HashSet<>();
  }
  public static <T> Queue<T> queue() {
    return new LinkedList<>();
  }
  // Examples:
  public static void main(String[] args) {
    Map<String, List<String>> sls = New.map();
    List<String> ls = New.list();
    LinkedList<String> lls = New.lList();
    Set<String> ss = New.set();
    Queue<String> qs = New.queue();
  }
} ///:~
