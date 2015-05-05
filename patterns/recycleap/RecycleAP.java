//: patterns/recycleap/RecycleAP.java 
// Recycling with RTTI and Prototypes.
package patterns.recycleap;
import patterns.trash.*;
import java.util.*;

public class RecycleAP {
  public static void main(String[] args) {
    ArrayList bin = new ArrayList();
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);
    ArrayList 
      glassBin = new ArrayList(),
      paperBin = new ArrayList(),
      alBin = new ArrayList();
    Iterator sorter = bin.iterator();
    // Sort the Trash:
    while(sorter.hasNext()) {
      Object t = sorter.next();
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
