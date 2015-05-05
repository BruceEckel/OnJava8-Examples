//: assertions/LoaderAssertions.java
// Using the class loader to enable assertions
// Compile with: javac -source 1.4 LoaderAssertions.java
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
