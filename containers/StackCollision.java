// containers/StackCollision.java
// ©2015 MindView LLC: see Copyright.txt

public class StackCollision {
  public static void main(String[] args) {
    com.mindviewinc.util.Stack<String> stack =
      new com.mindviewinc.util.Stack<>();
    for(String s : "My dog has fleas".split(" "))
      stack.push(s);
    while(!stack.empty())
      System.out.print(stack.pop() + " ");
    System.out.println();
    java.util.Stack<String> stack2 =
      new java.util.Stack<>();
    for(String s : "My dog has fleas".split(" "))
      stack2.push(s);
    while(!stack2.empty())
      System.out.print(stack2.pop() + " ");
  }
}
/* Output:
fleas has dog My
fleas has dog My
*/
