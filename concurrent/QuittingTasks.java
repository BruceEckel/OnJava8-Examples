// concurrent/QuittingTasks.java
// (c)2021 MindView LLC: see Copyright.txt
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
11 23 20 12 24 16 19 15 35 147 32 27 7 4 28 31 8 83 3 1
13 9 5 2 6 18 14 17 21 25 22 26 29 30 33 34 37 41 38 46
45 49 50 53 57 58 54 69 104 112 40 73 74 115 116 70 119
77 81 56 85 78 82 111 86 90 48 89 36 108 107 44 55 52
43 60 63 59 64 71 68 67 75 76 72 79 80 84 87 88 66 91
10 95 65 96 94 92 62 100 61 93 47 39 51 99 103 128 123
127 124 140 120 139 136 135 143 148 144 105 102 131 101
132 98 97 149 137 134 42 106 110 109 114 133 113 117
118 130 129 126 121 125 122 138 141 145 142 146
*/
