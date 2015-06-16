//: patterns/trashvisitor/VAluminum.java
// ©2015 MindView LLC: see Copyright.txt
// Aluminum for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class VAluminum extends Aluminum
    implements Visitable {
  public VAluminum(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
} ///:~
