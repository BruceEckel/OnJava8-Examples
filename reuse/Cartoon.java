// reuse/Cartoon.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Constructor calls during inheritance

import java.util.Arrays;

class Art {
  Art() {
    System.out.println("Art constructor");
  }
}

class Drawing extends Art {
  Drawing() {
    System.out.println("Drawing constructor");
  }
}

public class Cartoon extends Drawing {
  public Cartoon() {
    System.out.println("Cartoon constructor");
  }
  public static void main(String[] args) {
    Cartoon x = new Cartoon();
  }
}
/* Output:
Art constructor
Drawing constructor
Cartoon constructor
*/

// my extension code

class PP {
  PP() {
    System.out.println("PP constructor");
  }
}

class P extends PP {
  P() {
    System.out.println("P constructor");
  }

  public static void main(String[] args) {
    System.out.println("P main() args: " + Arrays.toString(args));
  }
}

class Child extends P {
  Child() {
    System.out.println("Child constructor");
  }

  public static void main(String[] args) {
    Child c = new Child();
    P.main(new String[]{"child", "parent"});
  }
}
