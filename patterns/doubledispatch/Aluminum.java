// patterns/doubledispatch/Aluminum.java
// ©2016 MindView LLC: see Copyright.txt
// Aluminum for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

public class Aluminum extends patterns.trash.Aluminum
    implements TypedBinMember {
  public Aluminum(double wt) { super(wt); }
  @Override
  public boolean addToBin(List<TypedBin> tbins) {
    return tbins.stream().anyMatch(tb -> tb.add(this));
  }
}
