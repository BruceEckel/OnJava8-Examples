// com/mindviewinc/util/Enums.java
// ©2015 MindView LLC: see Copyright.txt
package com.mindviewinc.util;
import java.util.*;

public class Enums {
  private static Random rand = new Random(47);
  public static <T extends Enum<T>> T random(Class<T> ec) {
    return random(ec.getEnumConstants());
  }
  public static <T> T random(T[] values) {
    return values[rand.nextInt(values.length)];
  }
}
