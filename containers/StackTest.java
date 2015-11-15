// containers/StackTest.java
// ©2016 MindView LLC: see Copyright.txt
import onjava.*;

public class StackTest {
  public static void main(String[] args) {
    Stack<String> stack = new Stack<>();
    for(String s : "My dog has fleas".split(" "))
      stack.push(s);
    while(!stack.empty())
      System.out.print(stack.pop() + " ");
  }
}
/* Output:
fleas has dog My
*/
