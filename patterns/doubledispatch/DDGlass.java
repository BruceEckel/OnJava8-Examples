//: patterns/doubledispatch/DDGlass.java
// Glass for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDGlass extends Glass 
    implements TypedBinMember {
  public DDGlass(double wt) { super(wt); }
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
