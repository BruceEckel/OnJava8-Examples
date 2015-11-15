// generics/InstantiateGenericType.java
// ©2016 MindView LLC: see Copyright.txt

class ClassAsFactory<T> {
  T x;
  public ClassAsFactory(Class<T> kind) {
    try {
      x = kind.newInstance();
    } catch(InstantiationException |
            IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }
}

class Employee {}

public class InstantiateGenericType {
  public static void main(String[] args) {
    ClassAsFactory<Employee> fe =
      new ClassAsFactory<>(Employee.class);
    System.out.println("ClassAsFactory<Employee> succeeded");
    try {
      ClassAsFactory<Integer> fi =
        new ClassAsFactory<>(Integer.class);
    } catch(Exception e) {
      System.out.println("ClassAsFactory<Integer> failed");
    }
  }
}
/* Output:
ClassAsFactory<Employee> succeeded
ClassAsFactory<Integer> failed
*/
