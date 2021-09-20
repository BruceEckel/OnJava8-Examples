// collections/NestedLocalRecords.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

public class NestedLocalRecords {
  record Nested(String s) {}
  void method() {
    record Local(String s) {}
  }
}
