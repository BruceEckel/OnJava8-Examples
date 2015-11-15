// streams/InterfaceWithDefault.java
// ©2016 MindView LLC: see Copyright.txt

interface InterfaceWithDefault {
  void firstMethod();
  void secondMethod();
  default void newMethod() {
    System.out.println("newMethod");
  }
}
