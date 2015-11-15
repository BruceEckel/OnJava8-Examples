// patterns/trashvisitor/Glass.java
// ©2016 MindView LLC: see Copyright.txt
// Glass for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class Glass extends patterns.trash.Glass
    implements Visitable {
  public Glass(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}
