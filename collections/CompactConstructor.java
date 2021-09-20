// collections/CompactConstructor.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

record Point(int x, int y) {
  void assertPositive(int val) {
    if(val < 0)
      throw new IllegalArgumentException("negative");
  }
  Point { // Compact: No parameter list
    assertPositive(x);
    assertPositive(y);
  }
}
