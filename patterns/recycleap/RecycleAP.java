//: patterns/recycleap/RecycleAP.java
// Recycling with RTTI and Prototypes.
package patterns.recycleap;
import patterns.trash.*;
import java.util.*;

public class RecycleAP {
  public static void main(String[] args) {
    ArrayList<Trash> bin = new ArrayList<>();
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);
    ArrayList<Trash>
      glassBin = new ArrayList<>(),
      paperBin = new ArrayList<>(),
      alBin = new ArrayList<>();
    Iterator<Trash> sorter = bin.iterator();
    // Sort the Trash:
    while(sorter.hasNext()) {
      Trash t = sorter.next();
      // RTTI to show class membership:
      if(t instanceof Aluminum)
        alBin.add(t);
      if(t instanceof Paper)
        paperBin.add(t);
      if(t instanceof Glass)
        glassBin.add(t);
    }
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
} ///:~
