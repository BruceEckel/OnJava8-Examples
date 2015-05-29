//: patterns/trashvisitor/VCardboard.java
// ©2015 MindView LLC: see Copyright.txt
// Cardboard for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class VCardboard extends Cardboard 
    implements Visitable {
  public VCardboard(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
} ///:~
