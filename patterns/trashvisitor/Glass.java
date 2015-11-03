// patterns/trashvisitor/Glass.java
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
