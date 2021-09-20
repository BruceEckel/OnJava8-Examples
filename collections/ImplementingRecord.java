// collections/ImplementingRecord.java
// (c)2021 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// {NewFeature} Since JDK 16

interface Star {
  double brightness();
  double density();
}

record RedDwarf(double brightness) implements Star {
  @Override public double density() { return 100.0; }
}
