//: containersindepth/ReadOnly.java
// ©2015 MindView LLC: see Copyright.txt
// Using the Collections.unmodifiable methods.
import java.util.*;
import com.mindviewinc.util.*;
import static com.mindviewinc.util.Print.*;

public class ReadOnly {
  static Collection<String> data =
    new ArrayList<>(Countries.names(6));
  public static void main(String[] args) {
    Collection<String> c =
      Collections.unmodifiableCollection(
        new ArrayList<>(data));
    print(c); // Reading is OK
    //! c.add("one"); // Can't change it

    List<String> a = Collections.unmodifiableList(
        new ArrayList<>(data));
    ListIterator<String> lit = a.listIterator();
    print(lit.next()); // Reading is OK
    //! lit.add("one"); // Can't change it

    Set<String> s = Collections.unmodifiableSet(
      new HashSet<>(data));
    print(s); // Reading is OK
    //! s.add("one"); // Can't change it

    // For a SortedSet:
    Set<String> ss = Collections.unmodifiableSortedSet(
      new TreeSet<>(data));

    Map<String,String> m = Collections.unmodifiableMap(
      new HashMap<>(Countries.capitals(6)));
    print(m); // Reading is OK
    //! m.put("Ralph", "Howdy!");

    // For a SortedMap:
    Map<String,String> sm =
      Collections.unmodifiableSortedMap(
        new TreeMap<>(Countries.capitals(6)));
  }
} /* Output:
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI]
ALGERIA
[BENIN, BOTSWANA, ANGOLA, BURKINA FASO, ALGERIA, BURUNDI]
{BENIN=Porto-Novo, BOTSWANA=Gaberone, ANGOLA=Luanda,
BURKINA FASO=Ouagadougou, ALGERIA=Algiers,
BURUNDI=Bujumbura}
*///:~
