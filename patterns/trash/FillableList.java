//: patterns/trash/FillableList.java
// Adapter that makes an ArrayList Fillable.
package patterns.trash;
import java.util.*;

public class FillableList
implements Fillable {
  private ArrayList<Trash> v;
  public FillableList(ArrayList<Trash> vv) {
    v = vv;
  }
  @Override
  public void addTrash(Trash t) { v.add(t); }
} ///:~
