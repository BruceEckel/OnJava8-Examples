//: patterns/doubledispatch/DDCardboard.java
// Cardboard for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDCardboard extends Cardboard 
    implements TypedBinMember {
  public DDCardboard(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for (TypedBin tb1 : tb) {
      if (tb1.add(this)) {
        return true;
      }
    }
    return false;
  }
} ///:~
