//: patterns/recycleb/RecycleB.java
// Adding more objects to the recycling problem.
package patterns.recycleb;
import patterns.trash.*;
import java.util.*;

// A vector that admits only the right type:
class Tbin<T> extends ArrayList<T> {
  Class binType;
  Tbin() {
    this.binType = binType;
  }
  boolean grab(T t) {
    // Comparing class types:
    if(t.getClass().equals(binType)) {
      add(t);
      return true; // Object grabbed
    }
    return false; // Object not grabbed
  }
}

class TbinList extends ArrayList { //(*1*)
  @SuppressWarnings("unchecked")
  boolean sort(Trash t) {
    Iterator e = iterator();
    while(e.hasNext()) {
      Tbin bin = (Tbin)e.next();
      if(bin.grab(t)) return true;
    }
    return false; // bin not found for t
  }
  void sortBin(Tbin bin) { // (*2*)
    Iterator e = bin.iterator();
    while(e.hasNext())
      if(!sort((Trash)e.next()))
        System.out.println("Bin not found");
  }
}

public class RecycleB {
  static Tbin<Trash> bin = new Tbin<>();
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);

    TbinList trashBins = new TbinList();
    trashBins.add(new Tbin<Aluminum>());
    trashBins.add(new Tbin<Paper>());
    trashBins.add(new Tbin<Glass>());
    // add one line here: (*3*)
    trashBins.add(new Tbin<Cardboard>());

    trashBins.sortBin(bin); // (*4*)

    Iterator<Tbin> e = trashBins.iterator();
    while(e.hasNext())
      Trash.sumValue(e.next());
    Trash.sumValue(bin);
  }
} ///:~
