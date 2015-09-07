// patterns/trash/FillableList.java
// ©2015 MindView LLC: see Copyright.txt
// Adapter that makes an ArrayList Fillable.
package patterns.trash;
import java.util.*;

public class FillableList<T extends Trash>
implements Fillable<T> {
  private ArrayList<T> v;
  public FillableList(ArrayList<T> vv) {
    v = vv;
  }
  @Override
  public void addTrash(T t) { v.add(t); }
}
