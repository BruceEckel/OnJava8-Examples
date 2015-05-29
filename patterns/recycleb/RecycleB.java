//: patterns/recycleb/RecycleB.java
// ©2015 MindView LLC: see Copyright.txt
// Adding more objects to the recycling problem.
package patterns.recycleb;
import patterns.trash.*;
import java.util.*;

// A List that admits only the right type:
class Tbin<T extends Trash> extends ArrayList<T> {
  Class<T> binType;
  Tbin(Class<T> type) {
    binType = type;
  }
  @SuppressWarnings("unchecked")
  boolean grab(Trash t) {
    // Comparing class types:
    if(t.getClass().equals(binType)) {
      add((T)t); // Downcast to this TBin's type
      return true; // Object grabbed
    }
    return false; // Object not grabbed
  }
}

class TbinList<T extends Trash> 
extends ArrayList<Tbin<? extends T>> { // (1)
  boolean sort(T t) {
    for(Tbin<? extends T> ts : this)
      if(ts.grab(t))
        return true;
    return false; // bin not found for t
  }
  void sortBin(Tbin<T> bin) { // (2)
    for(T aBin : bin)
      if(!sort(aBin))
        System.err.println("Bin not found");
  }
}

public class RecycleB {
  static Tbin<Trash> bin = new Tbin<>(Trash.class);
  public static void main(String[] args) {
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);

    TbinList<Trash> trashBins = new TbinList<>();
    trashBins.add(new Tbin<>(Aluminum.class));
    trashBins.add(new Tbin<>(Paper.class));
    trashBins.add(new Tbin<>(Glass.class));
    // add one line here: (3)
    trashBins.add(new Tbin<>(Cardboard.class));

    trashBins.sortBin(bin); // (4)

    trashBins.forEach(Trash::sumValue);
    Trash.sumValue(bin);
  }
} ///:~
