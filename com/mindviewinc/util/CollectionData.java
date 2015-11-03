// com/mindviewinc/util/CollectionData.java
// A Collection filled with data using a generator object.
package com.mindviewinc.util;
import java.util.*;
import java.util.function.*;

public class CollectionData<T> extends ArrayList<T> {
  public CollectionData(Supplier<T> gen, int quantity) {
    for(int i = 0; i < quantity; i++)
      add(gen.get());
  }
  // A generic convenience method:
  public static <T> CollectionData<T>
  list(Supplier<T> gen, int quantity) {
    return new CollectionData<>(gen, quantity);
  }
}
