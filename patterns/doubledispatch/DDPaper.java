//: patterns/doubledispatch/DDPaper.java
// Paper for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDPaper extends Paper 
    implements TypedBinMember {
  public DDPaper(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for(int i = 0; i < tb.length; i++)
      if(tb[i].add(this))
        return true;
    return false;
  }
} ///:~
