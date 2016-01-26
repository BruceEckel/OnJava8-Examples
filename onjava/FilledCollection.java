// onjava/FilledCollection.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// A Collection filled with data using a generator object
package onjava;
import java.util.*;
import java.util.function.*;

public class FilledCollection<T> extends ArrayList<T> {
  public FilledCollection(Supplier<T> gen, int quantity) {
    for(int i = 0; i < quantity; i++)
      add(gen.get());
  }
  // A generic convenience method:
  public static <T> FilledCollection<T>
  list(Supplier<T> gen, int quantity) {
    return new FilledCollection<>(gen, quantity);
  }
}
