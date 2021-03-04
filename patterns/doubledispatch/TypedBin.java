// patterns/doubledispatch/TypedBin.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// A List that can grab the right type.
package patterns.doubledispatch;
import patterns.trash.*;
import java.util.*;

public class TypedBin {
  private List<Trash> typedBin =
    new ArrayList<>();
  public final String type;
  public TypedBin(String type) {
    this.type = type;
  }
  public List<Trash> bin() {
    // Returns a copy of typedBin:
    return new ArrayList<Trash>(typedBin);
  }
  protected boolean addIt(Trash t) {
    typedBin.add(t);
    return true;
  }
  public boolean add(Aluminum a) {
    return false;
  }
  public boolean add(Paper a) {
    return false;
  }
  public boolean add(Glass a) {
    return false;
  }
  public boolean add(Cardboard a) {
    return false;
  }
}
