// patterns/doubledispatch/Cardboard.java
// Cardboard for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

public class Cardboard extends patterns.trash.Cardboard
    implements TypedBinMember {
  public Cardboard(double wt) { super(wt); }
  @Override
  public boolean addToBin(List<TypedBin> tbins) {
    return tbins.stream().anyMatch(tb -> tb.add(this));
  }
}
