// containers/PetMap.java
// ©2015 MindView LLC: see Copyright.txt
import typeinfo.pets.*;
import java.util.*;
import static com.mindviewinc.util.Print.*;

public class PetMap {
  public static void main(String[] args) {
    Map<String, Pet> petMap = new HashMap<>();
    petMap.put("My Cat", new Cat("Molly"));
    petMap.put("My Dog", new Dog("Ginger"));
    petMap.put("My Hamster", new Hamster("Bosco"));
    print(petMap);
    Pet dog = petMap.get("My Dog");
    print(dog);
    print(petMap.containsKey("My Dog"));
    print(petMap.containsValue(dog));
  }
}
/* Output:
{My Dog=Dog Ginger, My Cat=Cat Molly, My Hamster=Hamster
Bosco}
Dog Ginger
true
true
*/
