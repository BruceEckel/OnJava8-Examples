// generics/RestrictedComparablePets.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.

class Hamster extends ComparablePet
implements Comparable<ComparablePet> {
  @Override
  public int compareTo(ComparablePet arg) {
    return 0;
  }
}

// Or just:

class Gecko extends ComparablePet {
  public int compareTo(ComparablePet arg) {
    return 0;
  }
}
