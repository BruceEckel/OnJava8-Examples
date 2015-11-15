// patterns/trashvisitor/Paper.java
// ©2016 MindView LLC: see Copyright.txt
// Paper for the visitor pattern.
package patterns.trashvisitor;
import patterns.trash.*;

public class Paper extends patterns.trash.Paper
    implements Visitable {
  public Paper(double wt) { super(wt); }
  @Override
  public void accept(Visitor v) {
    v.visit(this);
  }
}
