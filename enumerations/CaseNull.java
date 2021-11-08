// enumerations/CaseNull.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Preview in JDK 17
// Compile with javac flags:
//   --enable-preview --source 17
// Run with java flag: --enable-preview
import java.util.*;
import java.util.function.*;

public class CaseNull {
  static void old(String s) {
    if(s == null) {
      System.out.println("null");
      return;
    }
    switch(s) {
      case "XX" -> System.out.println("XX");
      default   -> System.out.println("default");
    }
  }
  static void checkNull(String s) {
    switch(s) {
      case "XX" -> System.out.println("XX");
      case null -> System.out.println("null");
      default   -> System.out.println("default");
    }
    // Works with colon syntax, too:
    switch(s) {
      case "XX": System.out.println("XX");
                 break;
      case null: System.out.println("null");
                 break;
      default  : System.out.println("default");
    }
  }
  static void defaultOnly(String s) {
    switch(s) {
      case "XX" -> System.out.println("XX");
      default   -> System.out.println("default");
    }
  }
  static void combineNullAndCase(String s) {
    switch(s) {
      case "XX", null -> System.out.println("XX|null");
      default -> System.out.println("default");
    }
  }
  static void combineNullAndDefault(String s) {
    switch(s) {
      case "XX" -> System.out.println("XX");
      case null, default -> System.out.println("both");
    }
  }
  static void test(Consumer<String> cs) {
    cs.accept("XX");
    cs.accept("YY");
    try {
      cs.accept(null);
    } catch(NullPointerException e) {
      System.out.println(e.getMessage());
    }
  }
  public static void main(String[] args) {
    test(CaseNull::old);
    test(CaseNull::checkNull);
    test(CaseNull::defaultOnly);
    test(CaseNull::combineNullAndCase);
    test(CaseNull::combineNullAndDefault);
  }
}
/* Output:
XX
default
null
XX
XX
default
default
null
null
XX
default
Cannot invoke "String.hashCode()" because "<local1>" is null
XX|null
default
XX|null
XX
both
both
*/
