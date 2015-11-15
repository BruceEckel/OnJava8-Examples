// containersindepth/MapDataTest.java
// ©2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
import java.util.*;
import java.util.function.*;
import onjava.*;

class Letters implements Supplier<Pair<Integer,String>>,
  Iterable<Integer> {
  private int size = 9;
  private int number = 1;
  private char letter = 'A';
  @Override
  public Pair<Integer,String> get() {
    return new Pair<>(number++, "" + letter++);
  }
  @Override
  public Iterator<Integer> iterator() {
    return new Iterator<Integer>() {
      @Override
      public Integer next() { return number++; }
      @Override
      public boolean hasNext() { return number < size; }
      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }
}

public class MapDataTest {
  public static void main(String[] args) {
    // Pair Supplier:
    System.out.println(MapData.map(new Letters(), 11));
    // Two separate generators:
    System.out.println(MapData.map(new CountingSupplier.Character(),
      new RandomSupplier.String(3), 8));
    // A key Supplier and a single value:
    System.out.println(MapData.map(new CountingSupplier.Character(),
      "Value", 6));
    // An Iterable and a value Supplier:
    System.out.println(MapData.map(new Letters(),
      new RandomSupplier.String(3)));
    // An Iterable and a single value:
    System.out.println(MapData.map(new Letters(), "Pop"));
  }
}
/* Output:
{1=A, 2=B, 3=C, 4=D, 5=E, 6=F, 7=G, 8=H, 9=I, 10=J, 11=K}
{a=YNz, b=brn, c=yGc, d=FOW, e=ZnT, f=cQr, g=Gse, h=GZM}
{a=Value, b=Value, c=Value, d=Value, e=Value, f=Value}
{1=mJM, 2=RoE, 3=suE, 4=cUO, 5=neO, 6=EdL, 7=smw, 8=HLG}
{1=Pop, 2=Pop, 3=Pop, 4=Pop, 5=Pop, 6=Pop, 7=Pop, 8=Pop}
*/
