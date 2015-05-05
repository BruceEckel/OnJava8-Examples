//: patterns/trash/FillableArrayList.java 
// Adapter that makes an ArrayList Fillable.
package patterns.trash;
import java.util.*;

public class FillableArrayList 
implements Fillable {
  private ArrayList v;
  public FillableArrayList(ArrayList vv) { v = vv; }
  @Override
  public void addTrash(Trash t) {
    v.add(t);
  }
} ///:~
