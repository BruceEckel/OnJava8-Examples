// innerclasses/ClassInInterface.java
// ©2016 MindView LLC: see Copyright.txt
// {main: ClassInInterface$Test}

public interface ClassInInterface {
  void howdy();
  class Test implements ClassInInterface {
    @Override
    public void howdy() {
      System.out.println("Howdy!");
    }
    public static void main(String[] args) {
      new Test().howdy();
    }
  }
}
/* Output:
Howdy!
*/
