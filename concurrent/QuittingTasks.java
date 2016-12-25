// concurrent/QuittingTasks.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import onjava.Nap;

public class QuittingTasks {
  public static final int COUNT = 150;
  public static void main(String[] args) {
    ExecutorService es =
      Executors.newCachedThreadPool();
    List<QuittableTask> tasks =
      IntStream.range(1, COUNT)
        .mapToObj(QuittableTask::new)
        .peek(qt -> es.execute(qt))
        .collect(Collectors.toList());
    new Nap(1000);
    tasks.forEach(QuittableTask::quit);
    es.shutdown();
  }
}
/* Output:
125 148 115 127 120 118 106 140 77 119 97 80 143 17 92 147
89 123 16 12 138 25 13 101 135 96 76 73 130 133 37 132 134
149 137 122 29 49 60 40 142 131 53 1 98 145 126 65 5 64 82
79 68 86 141 61 128 22 7 26 19 139 114 146 14 15 43 34 10
75 87 90 31 47 38 62 18 63 41 42 144 66 23 6 4 91 70 83 102
103 54 69 74 56 71 94 88 78 81 57 52 93 45 48 44 32 28 36
33 104 105 112 109 100 117 24 108 21 116 20 9 85 8 84 72
107 113 121 124 136 129 99 95 55 3 27 2 59 67 50 58 51 39
30 35 46 110 111 11
*/
