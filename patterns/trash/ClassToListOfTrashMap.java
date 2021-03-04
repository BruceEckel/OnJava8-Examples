// patterns/trash/ClassToListOfTrashMap.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Display a Map<Class, List<Trash>>.
package patterns.trash;
import java.util.*;

public class ClassToListOfTrashMap {
  public static void
  show(Map<Class, List<Trash>> map) {
    map.values().forEach( bin -> {
      String typeName = "Trash";
      if(!bin.isEmpty())
        typeName =
          bin.get(0).getClass().getSimpleName();
      TrashValue.sum(bin, typeName);
    });
  }
}
