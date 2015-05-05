//: patterns/doubledispatch/DDAluminum.java
// Aluminum for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDAluminum extends Aluminum 
    implements TypedBinMember {
  public DDAluminum(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for(int i = 0; i < tb.length; i++)
      if(tb[i].add(this))
        return true;
    return false;
  }
} ///:~
