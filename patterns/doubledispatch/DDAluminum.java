//: patterns/doubledispatch/DDAluminum.java
// ©2015 MindView LLC: see Copyright.txt
// Aluminum for double dispatching.
package patterns.doubledispatch;
import patterns.trash.*;

public class DDAluminum extends Aluminum 
    implements TypedBinMember {
  public DDAluminum(double wt) { super(wt); }
  @Override
  public boolean addToBin(TypedBin[] tb) {
    for(TypedBin tb1 : tb) {
      if(tb1.add(this)) {
        return true;
      }
    }
    return false;
  }
} ///:~
