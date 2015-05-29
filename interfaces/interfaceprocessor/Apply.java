//: interfaces/interfaceprocessor/Apply.java
// ©2015 MindView LLC: see Copyright.txt
package interfaces.interfaceprocessor;
import static net.mindview.util.Print.*;

public class Apply {
  public static void process(Processor p, Object s) {
    print("Using Processor " + p.name());
    print(p.process(s));
  }
} ///:~
