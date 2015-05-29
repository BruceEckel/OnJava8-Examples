//: patterns/recycleap/RecycleAP.java
// ©2015 MindView LLC: see Copyright.txt
// Recycling with RTTI and Prototypes.
package patterns.recycleap;
import patterns.trash.*;
import java.util.*;

public class RecycleAP {
  public static void main(String[] args) {
    ArrayList<Trash> bin = new ArrayList<>();
    // Fill up the Trash bin:
    ParseTrash.fillBin("Trash.dat", bin);
    List<Glass> glassBin = new ArrayList<>();
    List<Paper> paperBin = new ArrayList<>();
    List<Aluminum> alBin = new ArrayList<>();
    // Sort the Trash:
    for(Trash t : bin) {
      // RTTI to discover Trash type:
      if(t instanceof Aluminum)
        alBin.add((Aluminum)t);
      if(t instanceof Paper)
        paperBin.add((Paper)t);
      if(t instanceof Glass)
        glassBin.add((Glass)t);
    }
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
} ///:~
