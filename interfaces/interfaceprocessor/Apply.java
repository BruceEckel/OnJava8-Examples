// interfaces/interfaceprocessor/Apply.java
// ©2016 MindView LLC: see Copyright.txt
package interfaces.interfaceprocessor;

public class Apply {
  public static void process(Processor p, Object s) {
    System.out.println("Using Processor " + p.name());
    System.out.println(p.process(s));
  }
}
