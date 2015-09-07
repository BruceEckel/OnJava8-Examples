// polymorphism/StaticPolymorphism.java
// ©2015 MindView LLC: see Copyright.txt
// Static methods are not polymorphic.

class StaticSuper {
  public static String staticGet() {
    return "Base staticGet()";
  }
  public String dynamicGet() {
    return "Base dynamicGet()";
  }
}

class StaticSub extends StaticSuper {
  public static String staticGet() {
    return "Derived staticGet()";
  }
  @Override
  public String dynamicGet() {
    return "Derived dynamicGet()";
  }
}

public class StaticPolymorphism {
  public static void main(String[] args) {
    StaticSuper sup = new StaticSub(); // Upcast
    System.out.println(StaticSuper.staticGet());
    System.out.println(sup.dynamicGet());
  }
}
/* Output:
Base staticGet()
Derived dynamicGet()
*/
