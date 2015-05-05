//: patterns/doubledispatch/DDCardboard.java
// Cardboard for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDCardboard extends Cardboard 
    implements TypedBinMember {
  public DDCardboard(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for(int i = 0; i < tb.length; i++)
      if(tb[i].add(this))
        return true;
    return false;
  }
} ///:~
