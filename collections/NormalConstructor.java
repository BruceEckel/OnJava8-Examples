// collections/NormalConstructor.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

record Value(int x) {
  Value(int x) { // With the parameter list
    this.x = x; // Must explicitly initialize
  }
}
