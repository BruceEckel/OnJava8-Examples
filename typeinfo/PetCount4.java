// typeinfo/PetCount4.java
// ©2016 MindView LLC: see Copyright.txt
import typeinfo.pets.*;
import onjava.*;

public class PetCount4 {
  public static void main(String[] args) {
    TypeCounter counter = new TypeCounter(Pet.class);
    for(Pet pet : Pets.createArray(20)) {
      System.out.print(pet.getClass().getSimpleName() + " ");
      counter.count(pet);
    }
    System.out.println();
    System.out.println(counter);
  }
}
/* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat
EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug
Mouse Cymric
{Rodent=5, Cat=9, Cymric=5, Manx=7, Hamster=1, Mouse=2,
Pug=3, Dog=6, Rat=2, Pet=20, EgyptianMau=2, Mutt=3}
*/
