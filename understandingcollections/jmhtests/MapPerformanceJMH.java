// understandingcollections/jmhtests/MapPerformanceJMH.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Demonstrates performance differences in Maps
package understandingcollections.jmhtests;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.util.*;

@State(Scope.Thread)
public class MapPerformanceJMH {
  private Map<Integer, Integer> map;

  @Param({"hashmap", "treemap"})
  private String type;

  private int begin;
  private int end;

  @Setup
  public void setup() {
    if (type.equals("hashmap")) {
      map = new HashMap<Integer, Integer>();
    } else if (type.equals("treemap")) {
      map = new TreeMap<Integer, Integer>();
    } else {
      throw new IllegalStateException("Unknown type: " + type);
    }

    begin = 1;
    end = 256;
    for (int i = begin; i < end; i++) {
      map.put(i, i);
    }
  }

  @Benchmark
  public void get(Blackhole bh) {
    for (int i = begin; i < end; i++) {
      bh.consume(map.get(i));
    }
  }

// These are incomplete placeholders from the
// original MapPerformance.jaava:
/*  @Benchmark
  int put() {
    for(int i = 0; i < loops; i++) {
      map.clear();
      for(int j = 0; j < size; j++)
        map.put(j, j);
    }
    return loops * size;
  }*/

/*  @Benchmark
  int iterate() {
    for(int i = 0; i < loops; i ++) {
      Iterator it = map.entrySet().iterator();
      while(it.hasNext())
        it.next();
    }
  }*/

}
