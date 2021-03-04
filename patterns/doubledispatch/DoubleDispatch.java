// patterns/doubledispatch/DoubleDispatch.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Using multiple dispatching to handle more
// than one unknown type during a method call.
// {java patterns.doubledispatch.DoubleDispatch}
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

class AluminumBin extends TypedBin {
  public AluminumBin() { super("Aluminum"); }
  @Override public boolean add(Aluminum a) {
    return addIt(a);
  }
}

class PaperBin extends TypedBin {
  public PaperBin() { super("Paper"); }
  @Override public boolean add(Paper a) {
    return addIt(a);
  }
}

class GlassBin extends TypedBin {
  public GlassBin() { super("Glass"); }
  @Override public boolean add(Glass a) {
    return addIt(a);
  }
}

class CardboardBin extends TypedBin {
  public CardboardBin() { super("Cardboard"); }
  @Override public boolean add(Cardboard a) {
    return addIt(a);
  }
}

class TrashBinSet {
  public final List<TypedBin> binSet =
    Arrays.asList(
      new AluminumBin(), new PaperBin(),
      new GlassBin(), new CardboardBin()
    );
  @SuppressWarnings("unchecked")
  public void sortIntoBins(List bin) {
    bin.forEach( aBin -> {
      TypedBinMember t = (TypedBinMember)aBin;
      if(!t.addToBin(binSet))
        throw new RuntimeException(
          "sortIntoBins() couldn't add " + t);
    });
  }
}

public class DoubleDispatch {
  public static void main(String[] args) {
    List<Trash> bin = new ArrayList<>();
    ParseTrash.fillBin("doubledispatch", bin);
    TrashBinSet bins = new TrashBinSet();
    // Sort from the master bin into the
    // individually-typed bins:
    bins.sortIntoBins(bin);
    // Sum value of each bin...
    bins.binSet.forEach(tb ->
      TrashValue.sum(tb.bin(), tb.type));
    // ... and for the master bin:
    TrashValue.sum(bin, "Trash");
  }
}
/* Output:
Loading patterns.doubledispatch.Cardboard
Loading patterns.doubledispatch.Paper
Loading patterns.doubledispatch.Aluminum
Loading patterns.doubledispatch.Glass
Aluminum weight: 1.80 * price: 1.67 = 3.01
Aluminum weight: 3.40 * price: 1.67 = 5.68
Aluminum weight: 2.70 * price: 1.67 = 4.51
Total Aluminum value = 13.19
Paper weight: 8.00 * price: 0.10 = 0.80
Paper weight: 6.60 * price: 0.10 = 0.66
Paper weight: 9.10 * price: 0.10 = 0.91
Total Paper value = 2.37
Glass weight: 5.40 * price: 0.23 = 1.24
Glass weight: 4.30 * price: 0.23 = 0.99
Glass weight: 3.60 * price: 0.23 = 0.83
Total Glass value = 3.06
Cardboard weight: 4.40 * price: 0.11 = 0.48
Cardboard weight: 2.20 * price: 0.11 = 0.24
Cardboard weight: 1.20 * price: 0.11 = 0.13
Total Cardboard value = 0.86
Cardboard weight: 4.40 * price: 0.11 = 0.48
Paper weight: 8.00 * price: 0.10 = 0.80
Aluminum weight: 1.80 * price: 1.67 = 3.01
Glass weight: 5.40 * price: 0.23 = 1.24
Aluminum weight: 3.40 * price: 1.67 = 5.68
Cardboard weight: 2.20 * price: 0.11 = 0.24
Glass weight: 4.30 * price: 0.23 = 0.99
Cardboard weight: 1.20 * price: 0.11 = 0.13
Paper weight: 6.60 * price: 0.10 = 0.66
Aluminum weight: 2.70 * price: 1.67 = 4.51
Paper weight: 9.10 * price: 0.10 = 0.91
Glass weight: 3.60 * price: 0.23 = 0.83
Total Trash value = 19.48
*/
