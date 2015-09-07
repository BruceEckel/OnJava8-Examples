// patterns/doubledispatch/DoubleDispatch.java
// ©2015 MindView LLC: see Copyright.txt
// Using multiple dispatching to handle more
// than one unknown type during a method call.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

class AluminumBin extends TypedBin {
  @Override
  public boolean add(DDAluminum a) {
    return addIt(a);
  }
}

class PaperBin extends TypedBin {
  @Override
  public boolean add(DDPaper a) {
    return addIt(a);
  }
}

class GlassBin extends TypedBin {
  @Override
  public boolean add(DDGlass a) {
    return addIt(a);
  }
}

class CardboardBin extends TypedBin {
  @Override
  public boolean add(DDCardboard a) {
    return addIt(a);
  }
}

class TrashBinSet {
  private TypedBin[] binSet = {
    new AluminumBin(),
    new PaperBin(),
    new GlassBin(),
    new CardboardBin()
  };
  public void sortIntoBins(ArrayList bin) {
    for(Object aBin : bin) {
      TypedBinMember t = (TypedBinMember)aBin;
      if(!t.addToBin(binSet))
        System.err.println("Couldn't add " + t);
    }
  }
  public TypedBin[] binSet() { return binSet; }
}

public class DoubleDispatch {
  public static void main(String[] args) {
    ArrayList<Trash> bin = new ArrayList<>();
    TrashBinSet bins = new TrashBinSet();
    // ParseTrash still works, without changes:
    ParseTrash.fillBin("DDTrash.dat", bin);
    // Sort from the master bin into the
    // individually-typed bins:
    bins.sortIntoBins(bin);
    // Perform sumValue for each bin...
    for(TypedBin tb1 : bins.binSet())
      Trash.sumValue(tb1.v);
    // ... and for the master bin
    Trash.sumValue(bin);
  }
}
/* Output: (First and last 10 Lines)
Loading patterns.doubledispatch.DDGlass
Loading patterns.doubledispatch.DDPaper
Loading patterns.doubledispatch.DDAluminum
Loading patterns.doubledispatch.DDCardboard
weight of patterns.doubledispatch.DDAluminum = 89.0
weight of patterns.doubledispatch.DDAluminum = 76.0
weight of patterns.doubledispatch.DDAluminum = 25.0
weight of patterns.doubledispatch.DDAluminum = 34.0
weight of patterns.doubledispatch.DDAluminum = 27.0
weight of patterns.doubledispatch.DDAluminum = 18.0
________...________...________...________...________
weight of patterns.doubledispatch.DDAluminum = 93.0
weight of patterns.doubledispatch.DDGlass = 93.0
weight of patterns.doubledispatch.DDPaper = 80.0
weight of patterns.doubledispatch.DDGlass = 36.0
weight of patterns.doubledispatch.DDGlass = 12.0
weight of patterns.doubledispatch.DDGlass = 60.0
weight of patterns.doubledispatch.DDPaper = 66.0
weight of patterns.doubledispatch.DDAluminum = 36.0
weight of patterns.doubledispatch.DDCardboard = 22.0
Total value = 1086.0599818825722
*/
