//: generics/CuriouslyRecurringGeneric.java
// ©2015 MindView LLC: see Copyright.txt

class GenericType<T> {}

public class CuriouslyRecurringGeneric
  extends GenericType<CuriouslyRecurringGeneric> {} ///:~
