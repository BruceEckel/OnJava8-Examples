// collectionsindepth/CollectionMethods.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Things you can do with all Collections
import java.util.*;
import onjava.*;

public class CollectionMethods {
  public static void main(String[] args) {
    Collection<String> c = new ArrayList<>();
    c.addAll(Countries.names(6));
    c.add("ten");
    c.add("eleven");
    System.out.println(c);
    // Make an array from the List:
    Object[] array = c.toArray();
    // Make a String array from the List:
    String[] str = c.toArray(new String[0]);
    // Find max and min elements; this means
    // different things depending on the way
    // the Comparable interface is implemented:
    System.out.println(
      "Collections.max(c) = " + Collections.max(c));
    System.out.println(
      "Collections.min(c) = " + Collections.min(c));
    // Add a Collection to another Collection
    Collection<String> c2 = new ArrayList<>();
    c2.addAll(Countries.names(6));
    c.addAll(c2);
    System.out.println(c);
    c.remove(Countries.DATA[0][0]);
    System.out.println(c);
    c.remove(Countries.DATA[1][0]);
    System.out.println(c);
    // Remove all components that are
    // in the argument collection:
    c.removeAll(c2);
    System.out.println(c);
    c.addAll(c2);
    System.out.println(c);
    // Is an element in this Collection?
    String val = Countries.DATA[3][0];
    System.out.println(
      "c.contains(" + val  + ") = " + c.contains(val));
    // Is a Collection in this Collection?
    System.out.println(
      "c.containsAll(c2) = " + c.containsAll(c2));
    Collection<String> c3 =
      ((List<String>)c).subList(3, 5);
    // Keep all the elements that are in both
    // c2 and c3 (an intersection of sets):
    c2.retainAll(c3);
    System.out.println(c2);
    // Throw away all the elements
    // in c2 that also appear in c3:
    c2.removeAll(c3);
    System.out.println("c2.isEmpty() = " +  c2.isEmpty());
    c = new ArrayList<>();
    c.addAll(Countries.names(6));
    System.out.println(c);
    c.clear(); // Remove all elements
    System.out.println("after c.clear():" + c);
  }
}
/* Output:
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI,
ten, eleven]
Collections.max(c) = ten
Collections.min(c) = ALGERIA
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI,
ten, eleven, ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA
FASO, BURUNDI]
[ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, ten,
eleven, ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO,
BURUNDI]
[BENIN, BOTSWANA, BURKINA FASO, BURUNDI, ten, eleven,
ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI]
[ten, eleven]
[ten, eleven, ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA
FASO, BURUNDI]
c.contains(BOTSWANA) = true
c.containsAll(c2) = true
[ANGOLA, BENIN]
c2.isEmpty() = true
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI]
after c.clear():[]
*/
