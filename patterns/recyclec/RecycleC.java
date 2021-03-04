// patterns/recyclec/RecycleC.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {java patterns.recyclec.RecycleC}
package patterns.recyclec;
import patterns.trash.*;
import java.util.*;

// A List that only admits the right type:
class
TrashBin<T extends Trash> extends ArrayList<T> {
  final Class<T> binType;
  TrashBin(Class<T> binType) {
    this.binType = binType;
  }
  @SuppressWarnings("unchecked")
  boolean grab(Trash t) {
    // Compare class types:
    if(t.getClass().equals(binType)) {
      add((T)t); // Downcast to this TrashBin type
      return true; // Trash grabbed
    }
    return false; // Trash not grabbed
  }
}

class TrashBinList<T extends Trash>
extends ArrayList<TrashBin<? extends T>> {    // [1]
  @SuppressWarnings("unchecked")
  public TrashBinList(Class<? extends T>... types) {
    for(Class<? extends T> type : types)
      add(new TrashBin<>(type));
  }
  public boolean sort(T t) {
    for(TrashBin<? extends T> ts : this)
      if(ts.grab(t))
        return true;
    return false; // TrashBin not found for t
  }
  public void sortBin(TrashBin<T> bin) {      // [2]
    for(T trash : bin)
      if(!sort(trash))
        throw new RuntimeException(
          "Bin not found for " + trash);
  }
  public void show() {
    for(TrashBin<? extends T> tbin : this) {
      String typeName = tbin.binType.getSimpleName();
      TrashValue.sum(tbin, typeName);
    }
  }
}

public class RecycleC {
  public static void main(String[] args) {
    TrashBin<Trash> bin =
      new TrashBin<>(Trash.class);
    ParseTrash.fillBin("trash", bin);
    @SuppressWarnings("unchecked")
    TrashBinList<Trash> trashBins =
      new TrashBinList<>(
        Aluminum.class, Paper.class, Glass.class,
        // Add one item:
        Cardboard.class                       // [3]
      );
    trashBins.sortBin(bin);                   // [4]
    trashBins.show();
    TrashValue.sum(bin, "Trash");
  }
}
/* Output:
Loading patterns.trash.Cardboard
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Glass
Aluminum weight: 1.80 * price: 1.67 = 3.01
Aluminum weight: 3.40 * price: 1.67 = 5.68
Aluminum weight: 2.70 * price: 1.67 = 4.51
Total Aluminum value = 13.19
Paper weight: 8.00 * price: 0.10 = 0.80
Paper weight: 6.60 * price: 0.10 = 0.66
Paper weight: 9.10 * price: 0.10 = 0.91
Total Paper value = 2.37
Glass weight: 5.40 * price: 0.23 = 1.24
Glass weight: 4.30 * price: 0.23 = 0.99
Glass weight: 3.60 * price: 0.23 = 0.83
Total Glass value = 3.06
Cardboard weight: 4.40 * price: 0.11 = 0.48
Cardboard weight: 2.20 * price: 0.11 = 0.24
Cardboard weight: 1.20 * price: 0.11 = 0.13
Total Cardboard value = 0.86
Cardboard weight: 4.40 * price: 0.11 = 0.48
Paper weight: 8.00 * price: 0.10 = 0.80
Aluminum weight: 1.80 * price: 1.67 = 3.01
Glass weight: 5.40 * price: 0.23 = 1.24
Aluminum weight: 3.40 * price: 1.67 = 5.68
Cardboard weight: 2.20 * price: 0.11 = 0.24
Glass weight: 4.30 * price: 0.23 = 0.99
Cardboard weight: 1.20 * price: 0.11 = 0.13
Paper weight: 6.60 * price: 0.10 = 0.66
Aluminum weight: 2.70 * price: 1.67 = 4.51
Paper weight: 9.10 * price: 0.10 = 0.91
Glass weight: 3.60 * price: 0.23 = 0.83
Total Trash value = 19.48
*/
