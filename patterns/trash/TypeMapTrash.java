// patterns/trash/TypeMapTrash.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Using a Map of Lists and reflection to sort
// Trash into Lists. This is extensible, despite
// the use of reflection.
// {java patterns.trash.TypeMapTrash}
package patterns.trash;
import patterns.TypeMap;

// Adapter class for ParseTrash.fillBin():
class TypeMapAdapter implements Fillable {
  private TypeMap<Trash> map;
  TypeMapAdapter(TypeMap<Trash> map) {
    this.map = map;
  }
  @Override
  public void addTrash(Trash t) { map.add(t); }
}

public class TypeMapTrash {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    TypeMap<Trash> bin = new TypeMap<>();
    ParseTrash.fillBin(
      "trash", new TypeMapAdapter(bin));
    ClassToListOfTrashMap.show(bin.map);
  }
}
/* Output:
Loading patterns.trash.Cardboard
Loading patterns.trash.Paper
Loading patterns.trash.Aluminum
Loading patterns.trash.Glass
Paper weight: 8.00 * price: 0.10 = 0.80
Paper weight: 6.60 * price: 0.10 = 0.66
Paper weight: 9.10 * price: 0.10 = 0.91
Total Paper value = 2.37
Glass weight: 5.40 * price: 0.23 = 1.24
Glass weight: 4.30 * price: 0.23 = 0.99
Glass weight: 3.60 * price: 0.23 = 0.83
Total Glass value = 3.06
Aluminum weight: 1.80 * price: 1.67 = 3.01
Aluminum weight: 3.40 * price: 1.67 = 5.68
Aluminum weight: 2.70 * price: 1.67 = 4.51
Total Aluminum value = 13.19
Cardboard weight: 4.40 * price: 0.11 = 0.48
Cardboard weight: 2.20 * price: 0.11 = 0.24
Cardboard weight: 1.20 * price: 0.11 = 0.13
Total Cardboard value = 0.86
*/
