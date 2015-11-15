// staticchecking/petspeak/PetSpeak.java
// ©2016 MindView LLC: see Copyright.txt
// Speaking pets in Java
package staticchecking.petspeak;

interface Pet {
  void speak();
}

class Cat implements Pet {
  public void speak() {
    System.out.println("meow!");
  }
}

class Dog implements Pet {
  public void speak() {
    System.out.println("woof!");
  }
}

public class PetSpeak {
  static void command(Pet p) { p.speak(); }
  public static void main(String[] args) {
    Pet[] pets = { new Cat(), new Dog() };
    for(Pet pet : pets)
      command(pet);
  }
}
/* Output:
meow!
woof!
*/
