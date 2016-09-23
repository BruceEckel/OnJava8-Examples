// onjava/Operation.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
package onjava;
import java.util.*;

public interface Operation {
  void execute();
  static void runOps(Operation... ops) {
    for(Operation op : ops)
      op.execute();
  }
  static void show(String msg) {
    System.out.println(msg);
  }
}
