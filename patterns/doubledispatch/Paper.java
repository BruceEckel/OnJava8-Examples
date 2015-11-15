// patterns/doubledispatch/Paper.java
// ©2016 MindView LLC: see Copyright.txt
// Paper for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

public class Paper extends patterns.trash.Paper
    implements TypedBinMember {
  public Paper(double wt) { super(wt); }
  @Override
  public boolean addToBin(List<TypedBin> tbins) {
    return tbins.stream().anyMatch(tb -> tb.add(this));
  }
}
