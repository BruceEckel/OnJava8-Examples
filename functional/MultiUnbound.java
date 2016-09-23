// functional/MultiUnbound.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
// Unbound methods with multiple arguments

class This {
  void two(int i, double d) {}
  void three(int i, double d, String s) {}
  void four(int i, double d, String s, char c) {}
}

interface TwoArgs {
  void call2(This _this, int i, double d);
}

interface ThreeArgs {
  void call3(This _this, int i, double d, String s);
}

interface FourArgs {
  void call4(
    This _this, int i, double d, String s, char c);
}

public class MultiUnbound {
  public static void main(String[] args) {
    TwoArgs twoargs = This::two;
    ThreeArgs threeargs = This::three;
    FourArgs fourargs = This::four;
    This _this = new This();
    twoargs.call2(_this, 11, 3.14);
    threeargs.call3(_this, 11, 3.14, "Three");
    fourargs.call4(_this, 11, 3.14, "Four", 'Z');
  }
}
