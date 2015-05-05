//: patterns/trashvisitor/VPaper.java
// Paper for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class VPaper extends Paper 
    implements Visitable {
  public VPaper(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
} ///:~
