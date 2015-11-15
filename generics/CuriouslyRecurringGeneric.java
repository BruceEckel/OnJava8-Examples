// generics/CuriouslyRecurringGeneric.java
// ©2016 MindView LLC: see Copyright.txt

class GenericType<T> {}

public class CuriouslyRecurringGeneric
  extends GenericType<CuriouslyRecurringGeneric> {}
