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
} /* Output: (First and last 10 Lines)
Loading patterns.trash.Glass
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Cardboard
weight of patterns.trash.Aluminum = 89.0
weight of patterns.trash.Aluminum = 76.0
weight of patterns.trash.Aluminum = 25.0
weight of patterns.trash.Aluminum = 34.0
weight of patterns.trash.Aluminum = 27.0
weight of patterns.trash.Aluminum = 18.0
________...________...________...________...________
weight of patterns.trash.Aluminum = 93.0
weight of patterns.trash.Glass = 93.0
weight of patterns.trash.Paper = 80.0
weight of patterns.trash.Glass = 36.0
weight of patterns.trash.Glass = 12.0
weight of patterns.trash.Glass = 60.0
weight of patterns.trash.Paper = 66.0
weight of patterns.trash.Aluminum = 36.0
weight of patterns.trash.Cardboard = 22.0
Total value = 1086.0599818825722
*///:~
