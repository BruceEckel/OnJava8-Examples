// typeinfo/PetCount3.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Using isInstance()
import java.util.*;
import java.util.stream.*;
import onjava.*;
import typeinfo.pets.*;

public class PetCount3 {
  static class Counter
  extends LinkedHashMap<Class<? extends Pet>, Integer> {
    public Counter() {
      super(LiteralPetCreator.ALL_TYPES.stream()
        .map(lpc -> Pair.make(lpc, 0))
        .collect(
          Collectors.toMap(Pair::key, Pair::value)));
    }
    public void count(Pet pet) {
      // Class.isInstance() eliminates instanceofs:
      for(Map.Entry<Class<? extends Pet>, Integer>
        pair : entrySet())
        if(pair.getKey().isInstance(pet))
          put(pair.getKey(), pair.getValue() + 1);
    }
    @Override
    public String toString() {
      StringBuilder result = new StringBuilder("{");
      for(Map.Entry<Class<? extends Pet>, Integer>
          pair : entrySet()) {
        result.append(pair.getKey().getSimpleName());
        result.append("=");
        result.append(pair.getValue());
        result.append(", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("}");
      return result.toString();
    }
  }
  public static void main(String[] args) {
    Counter petCount = new Counter();
    Pets.stream()
      .limit(20)
      .peek(petCount::count)
      .forEach(p -> System.out.print(
        p.getClass().getSimpleName() + " "));
    System.out.println("\n" + petCount);
  }
}
/* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat
EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug
Mouse Cymric
{Hamster=1, Rodent=5, Mouse=2, Dog=6, Cymric=5, Manx=7,
Rat=2, Pug=3, Mutt=3, Pet=20, EgyptianMau=2, Cat=9}
*/
