// generics/InstantiateGenericType.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.function.*;
import java.lang.reflect.InvocationTargetException;

class ClassAsFactory<T> implements Supplier<T> {
  Class<T> kind;
  ClassAsFactory(Class<T> kind) {
    this.kind = kind;
  }
  @Override public T get() {
    try {
      return kind.getConstructor().newInstance();
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }
}

class Employee {
  public Employee() {}
  @Override public String toString() {
    return "Employee";
  }
}

public class InstantiateGenericType {
  public static void main(String[] args) {
    ClassAsFactory<Employee> fe =
      new ClassAsFactory<>(Employee.class);
    System.out.println(fe.get());
    ClassAsFactory<Integer> fi =
      new ClassAsFactory<>(Integer.class);
    try {
      System.out.println(fi.get());
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
/* Output:
Employee
java.lang.NoSuchMethodException:
java.lang.Integer.<init>()
*/
