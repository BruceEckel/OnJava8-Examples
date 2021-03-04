// patterns/trash/DynaFactory.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Dynamic discovery of Trash types.
package patterns.trash;
import java.util.*;
import java.util.function.*;
import java.lang.reflect.*;

public class DynaFactory {
  private Map<String, Constructor> constructors =
    new HashMap<>();
  private String packageName;
  public DynaFactory(String packageName) {
    this.packageName = packageName;
  }
  @SuppressWarnings("unchecked")
  public
  <T extends Trash> T create(TrashInfo info) {
    try {
      String typename =
        "patterns." + packageName + "." + info.type;
      return (T)constructors.computeIfAbsent(
        typename, this::findConstructor
      ).newInstance(info.data);
    } catch(Exception e) {
      throw new RuntimeException(
        "Cannot create() Trash: " + info, e);
    }
  }
  private
  Constructor findConstructor(String typename) {
    try {
      System.out.println("Loading " + typename);
      return Class.forName(typename)
        .getConstructor(double.class);
    } catch(Exception e) {
      throw new RuntimeException(
        "Trash(double) Constructor Not Found: " +
        typename, e);
    }
  }
}
