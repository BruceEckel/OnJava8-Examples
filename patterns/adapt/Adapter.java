//: patterns/adapt/Adapter.java
// Variations on the Adapter pattern.
package patterns.adapt;

class WhatIHave {
  public void g() {}
  public void h() {}
}

interface WhatIWant {
  void f();
}

class ProxyAdapter implements WhatIWant {
  WhatIHave whatIHave;
  public ProxyAdapter(WhatIHave wih) {
    whatIHave = wih;
  }
  @Override
  public void f() {
    // Implement behavior using 
    // methods in WhatIHave:
    whatIHave.g();
    whatIHave.h();
  }
}
  
class WhatIUse {
  public void op(WhatIWant wiw) {
    wiw.f();
  }
}

// Approach 2: build adapter use into op():
class WhatIUse2 extends WhatIUse {
  public void op(WhatIHave wih) {
    new ProxyAdapter(wih).f();
  }
}

// Approach 3: build adapter into WhatIHave:
class WhatIHave2 extends WhatIHave 
implements WhatIWant {
  @Override
  public void f() {
    g();
    h();
  }
}

// Approach 4: use an inner class:
class WhatIHave3 extends WhatIHave {
  private class InnerAdapter implements WhatIWant{
    @Override
    public void f() {
      g();
      h();
    }
  }
  public WhatIWant whatIWant() { 
    return new InnerAdapter(); 
  }
}

public class Adapter {
  public static void main(String args[]) {
    WhatIUse whatIUse = new WhatIUse();
    WhatIHave whatIHave = new WhatIHave();
    WhatIWant adapt= new ProxyAdapter(whatIHave);
    whatIUse.op(adapt);
    // Approach 2:
    WhatIUse2 whatIUse2 = new WhatIUse2();
    whatIUse2.op(whatIHave);
    // Approach 3:
    WhatIHave2 whatIHave2 = new WhatIHave2();
    whatIUse.op(whatIHave2);
    // Approach 4:
    WhatIHave3 whatIHave3 = new WhatIHave3();
    whatIUse.op(whatIHave3.whatIWant());
  }
} ///:~
