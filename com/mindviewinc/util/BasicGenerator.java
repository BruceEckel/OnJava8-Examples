// com/mindviewinc/util/BasicGenerator.java
// ©2015 MindView LLC: see Copyright.txt
// Automatically create a Generator, given a class
// with a default (no-arg) constructor.
package com.mindviewinc.util;

public class BasicGenerator<T> implements Generator<T> {
  private Class<T> type;
  public BasicGenerator(Class<T> type){ this.type = type; }
  @Override
  public T next() {
    try {
      // Assumes type is a public class:
      return type.newInstance();
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
  // Produce a Default generator given a type token:
  public static <T> Generator<T> create(Class<T> type) {
    return new BasicGenerator<>(type);
  }
}
