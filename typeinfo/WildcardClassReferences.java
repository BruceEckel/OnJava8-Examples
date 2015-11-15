// typeinfo/WildcardClassReferences.java
// ©2016 MindView LLC: see Copyright.txt

public class WildcardClassReferences {
  public static void main(String[] args) {
    Class<?> intClass = int.class;
    intClass = double.class;
  }
}
/* Output: (None) */
