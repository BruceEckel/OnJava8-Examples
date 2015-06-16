//: typeinfo/PetCount4.java
// ©2015 MindView LLC: see Copyright.txt
import typeinfo.pets.*;
import com.mindviewinc.util.*;
import static com.mindviewinc.util.Print.*;

public class PetCount4 {
  public static void main(String[] args) {
    TypeCounter counter = new TypeCounter(Pet.class);
    for(Pet pet : Pets.createArray(20)) {
      printnb(pet.getClass().getSimpleName() + " ");
      counter.count(pet);
    }
    print();
    print(counter);
  }
} /* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat
EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug
Mouse Cymric
{Rodent=5, Cat=9, Cymric=5, Manx=7, Hamster=1, Mouse=2,
Pug=3, Dog=6, Rat=2, Pet=20, EgyptianMau=2, Mutt=3}
*///:~
