// patterns/doubledispatch/Glass.java
// ©2016 MindView LLC: see Copyright.txt
// Glass for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

public class Glass extends patterns.trash.Glass
    implements TypedBinMember {
  public Glass(double wt) { super(wt); }
  @Override
  public boolean addToBin(List<TypedBin> tbins) {
    return tbins.stream().anyMatch(tb -> tb.add(this));
  }
}
