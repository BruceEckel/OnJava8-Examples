//: assertions/LoaderAssertions.java
// ©2015 MindView LLC: see Copyright.txt
// Using the class loader to enable assertions
// {ThrowsException}

public class LoaderAssertions {
  public static void main(String[] args) {
    ClassLoader.getSystemClassLoader()
      .setDefaultAssertionStatus(true);
    new Loaded().go();
  }
}

class Loaded {
  public void go() {
    assert false: "Loaded.go()";
  }
} ///:~
