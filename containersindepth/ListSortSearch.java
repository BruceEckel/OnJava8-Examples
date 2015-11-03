// containersindepth/ListSortSearch.java
// Sorting and searching Lists with Collections utilities.
import java.util.*;

public class ListSortSearch {
  public static void main(String[] args) {
    List<String> list =
      new ArrayList<>(Utilities.list);
    list.addAll(Utilities.list);
    System.out.println(list);
    Collections.shuffle(list, new Random(47));
    System.out.println("Shuffled: " + list);
    // Use a ListIterator to trim off the last elements:
    ListIterator<String> it = list.listIterator(10);
    while(it.hasNext()) {
      it.next();
      it.remove();
    }
    System.out.println("Trimmed: " + list);
    Collections.sort(list);
    System.out.println("Sorted: " + list);
    String key = list.get(7);
    int index = Collections.binarySearch(list, key);
    System.out.println("Location of " + key + " is " + index +
      ", list.get(" + index + ") = " + list.get(index));
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    System.out.println("Case-insensitive sorted: " + list);
    key = list.get(7);
    index = Collections.binarySearch(list, key,
      String.CASE_INSENSITIVE_ORDER);
    System.out.println("Location of " + key + " is " + index +
      ", list.get(" + index + ") = " + list.get(index));
  }
}
/* Output:
[one, Two, three, Four, five, six, one, one, Two, three,
Four, five, six, one]
Shuffled: [Four, five, one, one, Two, six, six, three,
three, five, Four, Two, one, one]
Trimmed: [Four, five, one, one, Two, six, six, three,
three, five]
Sorted: [Four, Two, five, five, one, one, six, six, three,
three]
Location of six is 7, list.get(7) = six
Case-insensitive sorted: [five, five, Four, one, one, six,
six, three, three, Two]
Location of three is 7, list.get(7) = three
*/
