//: patterns/doubledispatch/DoubleDispatch.java
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
} ///:~
