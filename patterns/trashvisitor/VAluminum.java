//: patterns/trashvisitor/VAluminum.java
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
