// patterns/trashvisitor/Aluminum.java
// Aluminum for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class Aluminum extends patterns.trash.Aluminum
    implements Visitable {
  public Aluminum(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}
