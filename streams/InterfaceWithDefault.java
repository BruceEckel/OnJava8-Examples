// streams/InterfaceWithDefault.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

interface InterfaceWithDefault {
  void firstMethod();
  void secondMethod();
  default void newMethod() {
    System.out.println("newMethod");
  }
}
