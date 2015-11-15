// streams/Operation.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;

@FunctionalInterface
public interface Operation {
  void execute();
  static void runOps(List<Operation> ops) {
    ops.forEach(Operation::execute);
  }
  static void show(String msg) {
    System.out.println(msg);
  }
}
