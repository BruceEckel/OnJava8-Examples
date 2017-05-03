// concurrent/QuittingTasks.java
// (c)2017 MindView LLC: see Copyright.txt
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
    new Nap(1);
    tasks.forEach(QuittableTask::quit);
    es.shutdown();
  }
}
/* Output:
2 33 32 28 31 30 26 29 27 22 1 25 4 23 24 6 5 7 3 8 10 9 12
11 21 17 13 14 15 16 18 19 20 39 34 36 37 35 38 40 52 57 53
54 95 88 89 91 84 90 87 85 92 86 77 94 80 81 78 59 55 71 74
60 58 76 79 62 83 63 82 61 64 68 65 72 69 67 66 47 75 73 49
45 70 42 44 51 41 43 50 46 48 56 123 126 119 120 127 121
114 117 99 116 115 129 96 145 142 143 148 93 140 125 139
124 147 101 141 128 133 100 135 137 130 144 134 146 131 149
138 113 132 110 109 111 105 118 97 122 107 98 102 104 103
106 112 108 136
*/
