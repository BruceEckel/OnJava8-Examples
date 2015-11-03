// exceptions/ExceptionMethods.java
// Demonstrating the Exception Methods.

public class ExceptionMethods {
  public static void main(String[] args) {
    try {
      throw new Exception("My Exception");
    } catch(Exception e) {
      System.out.println("Caught Exception");
      System.out.println("getMessage():" + e.getMessage());
      System.out.println("getLocalizedMessage():" +
        e.getLocalizedMessage());
      System.out.println("toString():" + e);
      System.out.println("printStackTrace():");
      e.printStackTrace(System.out);
    }
  }
}
/* Output:
Caught Exception
getMessage():My Exception
getLocalizedMessage():My Exception
toString():java.lang.Exception: My Exception
printStackTrace():
java.lang.Exception: My Exception
        at ExceptionMethods.main(ExceptionMethods.java:8)
*/
