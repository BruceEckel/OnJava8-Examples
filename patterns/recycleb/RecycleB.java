//: patterns/recycleb/RecycleB.java
// Adding more objects to the recycling problem.
package patterns.recycleb;
import patterns.trash.*;
import java.util.*;

// A vector that admits only the right type:
class Tbin extends ArrayList {
  Class binType;
  Tbin(Class binType) { 
    this.binType = binType; 
  }
  boolean grab(Trash t) {
    // Comparing class types:
    if(t.getClass().equals(binType)) {
      add(t);
      return true; // Object grabbed
    }
    return false; // Object not grabbed
  }
}

class TbinList extends ArrayList { //(*1*)
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
  static Tbin bin = new Tbin(Trash.class);
  public static void main(String[] args) {
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);

    TbinList trashBins = new TbinList();
    trashBins.add(
      new Tbin(Aluminum.class));
    trashBins.add(
      new Tbin(Paper.class));
    trashBins.add(
      new Tbin(Glass.class));
    // add one line here: (*3*)
    trashBins.add(
      new Tbin(Cardboard.class));

    trashBins.sortBin(bin); // (*4*)

    Iterator e = trashBins.iterator();
    while(e.hasNext()) {
      Tbin b = (Tbin)e.next();
      Trash.sumValue(b);
    }
    Trash.sumValue(bin);
  }
} ///:~
