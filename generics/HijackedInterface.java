// generics/HijackedInterface.java
// ©2016 MindView LLC: see Copyright.txt
// {CompileTimeError} (Won't compile)

class Cat extends ComparablePet implements Comparable<Cat>{
  // Error: Comparable cannot be inherited with
  // different arguments: <Cat> and <Pet>
  public int compareTo(Cat arg) { return 0; }
}
