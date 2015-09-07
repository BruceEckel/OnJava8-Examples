// patterns/doubledispatch/DDPaper.java
// ©2015 MindView LLC: see Copyright.txt
// Paper for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDPaper extends Paper
    implements TypedBinMember {
  public DDPaper(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for(TypedBin tb1 : tb) {
      if(tb1.add(this)) {
        return true;
      }
    }
    return false;
  }
}
