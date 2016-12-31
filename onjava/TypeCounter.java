// onjava/TypeCounter.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Counts instances of a type family
package onjava;
import java.util.*;

public class
TypeCounter extends HashMap<Class<?>, Integer> {
  private Class<?> baseType;
  public TypeCounter(Class<?> baseType) {
    this.baseType = baseType;
  }
  public void count(Object obj) {
    Class<?> type = obj.getClass();
    if(!baseType.isAssignableFrom(type))
      throw new RuntimeException(
        obj + " incorrect type: " + type +
        ", should be type or subtype of " + baseType);
    countClass(type);
  }
  private void countClass(Class<?> type) {
    Integer quantity = get(type);
    put(type, quantity == null ? 1 : quantity + 1);
    Class<?> superClass = type.getSuperclass();
    if(superClass != null &&
       baseType.isAssignableFrom(superClass))
      countClass(superClass);
  }
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("{");
    for(Map.Entry<Class<?>, Integer> pair : entrySet()) {
      result.append(pair.getKey().getSimpleName());
      result.append("=");
      result.append(pair.getValue());
      result.append(", ");
    }
    result.delete(result.length() - 2, result.length());
    result.append("}");
    return result.toString();
  }
}
