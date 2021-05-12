// interfaces/Factories.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

interface Service {
  void method1();
  void method2();
}

interface ServiceFactory {
  Service getService();
}

class Service1 implements Service {
  Service1() {} // Package access
  @Override public void method1() {
    System.out.println("Service1 method1");
  }
  @Override public void method2() {
    System.out.println("Service1 method2");
  }
}

class Service1Factory implements ServiceFactory {
  @Override public Service getService() {
    return new Service1();
  }
}

class Service2 implements Service {
  Service2() {} // Package access
  @Override public void method1() {
    System.out.println("Service2 method1");
  }
  @Override public void method2() {
    System.out.println("Service2 method2");
  }
}

class Service2Factory implements ServiceFactory {
  @Override public Service getService() {
    return new Service2();
  }
}

public class Factories {
  public static void serviceConsumer(ServiceFactory fact) {
    Service s = fact.getService();
    s.method1();
    s.method2();
  }
  public static void main(String[] args) {
    serviceConsumer(new Service1Factory());
    // Services are completely interchangeable:
    serviceConsumer(new Service2Factory());
  }
}
/* Output:
Service1 method1
Service1 method2
Service2 method1
Service2 method2
*/

// My simulation
interface Computer {
  void playMusic();
  void coding();
}

interface ComputerFactory {
  Computer getComputer();
}

class MicrosoftComputer implements Computer {
  @Override public void playMusic() {
    System.out.println("MicrosoftComputer playMusic");
  }
  @Override public void coding() {
    System.out.println("MicrosoftComputer coding");
  }
}

class MicrosoftComputerFacotry implements ComputerFactory {
  @Override public Computer getComputer() {
    return new MicrosoftComputer();
  }
}

class AppleComputer implements Computer {
  @Override public void playMusic() {
    System.out.println("AppleComputer playMusic");
  }
  @Override public void coding() {
    System.out.println("AppleComputer coding");
  }
}

class AppleComputerFactory implements ComputerFactory {
  @Override public Computer getComputer() {
    return new AppleComputer();
  }
}

 class Test {
  public static void show(ComputerFactory fact) {
    Computer c = fact.getComputer();
    c.playMusic();
    c.coding();
  }

   public static void main(String[] args) {
     show(new AppleComputerFactory());
     show(new MicrosoftComputerFacotry());
   }
}





