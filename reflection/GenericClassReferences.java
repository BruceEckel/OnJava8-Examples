// reflection/GenericClassReferences.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

public class GenericClassReferences {
  public static void main(String[] args) {
    Class intClass = int.class;
    intClass = double.class;
    Class<Integer> genericIntClass = int.class;
    genericIntClass = Integer.class; // Same thing
    // genericIntClass = double.class; // Illegal
  }
}
