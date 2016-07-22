// typeinfo/PetCount4.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import typeinfo.pets.*;
import onjava.*;

public class PetCount4 {
  public static void main(String[] args) {
    TypeCounter counter = new TypeCounter(Pet.class);
    Pets.stream()
      .limit(20)
      .peek(counter::count)
      .forEach(p -> System.out.print(
        p.getClass().getSimpleName() + " "));
    System.out.println("\n" + counter);
  }
}
/* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat
EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug
Mouse Cymric
{EgyptianMau=2, Hamster=1, Pug=3, Manx=7, Cymric=5, Dog=6,
Mutt=3, Rat=2, Mouse=2, Rodent=5, Pet=20, Cat=9}
*/
