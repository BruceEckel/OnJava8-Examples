// collectionsindepth/MapPerformance.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates performance differences in Maps
// {Args: 100 5000} Small to keep build testing short
import java.util.*;

public class MapPerformance {
  static List<Test<Map<Integer,Integer>>> tests =
    new ArrayList<>();
  static {
    tests.add(new Test<Map<Integer,Integer>>("put") {
      @Override
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops;
        int size = tp.size;
        for(int i = 0; i < loops; i++) {
          map.clear();
          for(int j = 0; j < size; j++)
            map.put(j, j);
        }
        return loops * size;
      }
    });
    tests.add(new Test<Map<Integer,Integer>>("get") {
      @Override
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops;
        int span = tp.size * 2;
        for(int i = 0; i < loops; i++)
          for(int j = 0; j < span; j++)
            map.get(j);
        return loops * span;
      }
    });
    tests.add(new Test<Map<Integer,Integer>>("iterate") {
      @Override
      int test(Map<Integer,Integer> map, TestParam tp) {
        int loops = tp.loops * 10;
        for(int i = 0; i < loops; i ++) {
          Iterator it = map.entrySet().iterator();
          while(it.hasNext())
            it.next();
        }
        return loops * map.size();
      }
    });
  }
  public static void main(String[] args) {
    if(args.length > 0)
      Tester.defaultParams = TestParam.array(args);
    Tester.run(new TreeMap<>(), tests);
    Tester.run(new HashMap<>(), tests);
    Tester.run(new LinkedHashMap<>(),tests);
    Tester.run(
      new IdentityHashMap<>(), tests);
    Tester.run(new WeakHashMap<>(), tests);
    Tester.run(new Hashtable<>(), tests);
  }
}
/* Output:
---------- TreeMap ----------
 size     put     get iterate
  100      86      47       9
---------- HashMap ----------
 size     put     get iterate
  100      25      10      12
------- LinkedHashMap -------
 size     put     get iterate
  100      27       8       6
------ IdentityHashMap ------
 size     put     get iterate
  100      51      47      13
-------- WeakHashMap --------
 size     put     get iterate
  100      28      11      12
--------- Hashtable ---------
 size     put     get iterate
  100      31      21      13
*/
