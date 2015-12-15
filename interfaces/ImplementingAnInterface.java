// interfaces/ImplementingAnInterface.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.

interface Concept { // Package access
  void idea1();
  void idea2();
}

class Implementation implements Concept {
  public void idea1() { System.out.println("idea1"); }
  public void idea2() { System.out.println("idea2"); }
}
