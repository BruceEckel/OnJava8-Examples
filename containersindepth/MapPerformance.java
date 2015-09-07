// containersindepth/MapPerformance.java
// ©2015 MindView LLC: see Copyright.txt
// Demonstrates performance differences in Maps.
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
  100     137      99      24
---------- HashMap ----------
size     put     get iterate
  100      47      19      29
------- LinkedHashMap -------
size     put     get iterate
  100      59      13      11
------ IdentityHashMap ------
size     put     get iterate
  100      74      71      23
-------- WeakHashMap --------
size     put     get iterate
  100      43      16      15
--------- Hashtable ---------
size     put     get iterate
  100      32      27      17
*/
