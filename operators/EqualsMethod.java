// operators/EqualsMethod.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Default equals() does not compare contents

import java.util.Objects;

class ValA {
  int i;
}

class ValB {
  int i;
  // Works for this example, not a complete equals():
  public boolean equals(Object o) {
    ValB rval = (ValB)o;  // Cast o to be a ValB
    return i == rval.i;
  }
}

public class EqualsMethod {
  public static void main(String[] args) {
    ValA va1 = new ValA();
    ValA va2 = new ValA();
    va1.i = va2.i = 100;
    System.out.println(va1.equals(va2));
    ValB vb1 = new ValB();
    ValB vb2 = new ValB();
    vb1.i = vb2.i = 100;
    System.out.println(vb1.equals(vb2));
  }
}
/* Output:
false
true
*/

// my extension code

class Man {
  int age;
  String name;
  public Man(int age, String name) {
    this.age = age;
    this.name = name;
  }
}

class Woman {
  int age;
  String name;
  public Woman(int age, String name) {
    this.age = age;
    this.name = name;
  }
  public boolean equals(Object o) {
    Woman woman = (Woman) o;
    return age == woman.age && Objects.equals(name, woman.name);
  }
}

class TestClass {
  public static void main(String[] args) {
    Man man1 = new Man(20, "man");
    Man man2 = new Man(20, "man");
    System.out.println(man1.equals(man2)); // false
    Woman woman1 = new Woman(20, "woman");
    Woman woman2 = new Woman(20, "woman");
    System.out.println(woman1.equals(woman2)); // true
  }
}



