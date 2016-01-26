// arrays/TestCount.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Test counting generators
import java.util.*;
import java.util.stream.*;
import onjava.*;
import static onjava.ArrayShow.*;

public class TestCount {
  static final int SZ = 5;
  public static void main(String[] args) {
    System.out.println("Boolean");
    Boolean[] a1 = new Boolean[SZ];
    Arrays.setAll(a1, new Count.Boolean()::get);
    show(a1);
    show(Stream.generate(new Count.Boolean())
      .limit(SZ + 1).toArray());
    show(new Count.Boolean().array(SZ + 2));
    show(new Count.boolean_().array(SZ + 3));

    System.out.println("Byte");
    Byte[] a2 = new Byte[SZ];
    Arrays.setAll(a2, new Count.Byte()::get);
    show(a2);
    show(Stream.generate(new Count.Byte())
      .limit(SZ + 1).toArray());
    show(new Count.Byte().array(SZ + 2));
    show(new Count.byte_().array(SZ + 3));

    System.out.println("Character");
    Character[] a3 = new Character[SZ];
    Arrays.setAll(a3, new Count.Character()::get);
    show(a3);
    show(Stream.generate(new Count.Character())
      .limit(SZ + 1).toArray());
    show(new Count.Character().array(SZ + 2));
    show(new Count.char_().array(SZ + 3));

    System.out.println("Short");
    Short[] a4 = new Short[SZ];
    Arrays.setAll(a4, new Count.Short()::get);
    show(a4);
    show(Stream.generate(new Count.Short())
      .limit(SZ + 1).toArray());
    show(new Count.Short().array(SZ + 2));
    show(new Count.short_().array(SZ + 3));

    System.out.println("Integer");
    int[] a5 = new int[SZ];
    Arrays.setAll(a5, new Count.Integer()::get);
    show(a5);
    show(Stream.generate(new Count.Integer())
      .limit(SZ + 1).toArray());
    show(new Count.Integer().array(SZ + 2));
    a5 = IntStream.generate(new Count.int_())
      .limit(SZ + 1).toArray();
    show(a5);
    show(new Count.int_().array(SZ + 3));

    System.out.println("Long");
    long[] a6 = new long[SZ];
    Arrays.setAll(a6, new Count.Long()::get);
    show(a6);
    show(Stream.generate(new Count.Long())
      .limit(SZ + 1).toArray());
    show(new Count.Long().array(SZ + 2));
    a6 = LongStream.generate(new Count.long_())
      .limit(SZ + 1).toArray();
    show(a6);
    show(new Count.long_().array(SZ + 3));

    System.out.println("Float");
    Float[] a7 = new Float[SZ];
    Arrays.setAll(a7, new Count.Float()::get);
    show(a7);
    show(Stream.generate(new Count.Float())
      .limit(SZ + 1).toArray());
    show(new Count.Float().array(SZ + 2));
    show(new Count.float_().array(SZ + 3));

    System.out.println("Double");
    double[] a8 = new double[SZ];
    Arrays.setAll(a8, new Count.Double()::get);
    show(a8);
    show(Stream.generate(new Count.Double())
      .limit(SZ + 1).toArray());
    show(new Count.Double().array(SZ + 2));
    a8 = DoubleStream.generate(new Count.double_())
      .limit(SZ + 1).toArray();
    show(a8);
    show(new Count.double_().array(SZ + 3));
  }
}
/* Output:
Boolean
[false, true, false, true, false]
[false, true, false, true, false, true]
[false, true, false, true, false, true, false]
[false, true, false, true, false, true, false, true]
Byte
[0, 1, 2, 3, 4]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6]
[0, 1, 2, 3, 4, 5, 6, 7]
Character
[b, c, d, e, f]
[b, c, d, e, f, g]
[b, c, d, e, f, g, h]
[b, c, d, e, f, g, h, i]
Short
[0, 1, 2, 3, 4]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6]
[0, 1, 2, 3, 4, 5, 6, 7]
Integer
[0, 1, 2, 3, 4]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6, 7]
Long
[0, 1, 2, 3, 4]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6]
[0, 1, 2, 3, 4, 5]
[0, 1, 2, 3, 4, 5, 6, 7]
Float
[0.0, 1.0, 2.0, 3.0, 4.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0]
Double
[0.0, 1.0, 2.0, 3.0, 4.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0]
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0]
*/
