//: innerclasses/Factories.java
// ©2015 MindView LLC: see Copyright.txt
import static com.mindviewinc.util.Print.*;

interface Service {
  void method1();
  void method2();
}

interface ServiceFactory {
  Service getService();
}

class Implementation1 implements Service {
  private Implementation1() {}
  public void method1() {print("Implementation1 method1");}
  public void method2() {print("Implementation1 method2");}
  public static ServiceFactory factory =
    new ServiceFactory() {
      public Service getService() {
        return new Implementation1();
      }
    };
}

class Implementation2 implements Service {
  private Implementation2() {}
  public void method1() {print("Implementation2 method1");}
  public void method2() {print("Implementation2 method2");}
  // Use method reference instead:
  public static ServiceFactory factory =
    Implementation2::new; // Constructor reference
}

class Implementation3 implements Service {
  private Implementation3() {}
  public void method1() {print("Implementation3 method1");}
  public void method2() {print("Implementation3 method2");}
  // Use lambda expression instead:
  public static ServiceFactory factory =
    () -> new Implementation3();
}

public class Factories {
  public static void serviceConsumer(ServiceFactory fact) {
    Service s = fact.getService();
    s.method1();
    s.method2();
  }
  public static void main(String[] args) {
    serviceConsumer(Implementation1.factory);
    // Implementations are completely interchangeable:
    serviceConsumer(Implementation2.factory);
    serviceConsumer(Implementation3.factory);
  }
} /* Output:
Implementation1 method1
Implementation1 method2
Implementation2 method1
Implementation2 method2
Implementation3 method1
Implementation3 method2
*///:~
