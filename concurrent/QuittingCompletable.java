// concurrent/QuittingCompletable.java
// (c)2017 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://OnJava8.com for more book information.
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import onjava.Nap;

public class QuittingCompletable {
  public static void main(String[] args) {
    List<QuittableTask> tasks =
      IntStream.range(1, QuittingTasks.COUNT)
        .mapToObj(QuittableTask::new)
        .collect(Collectors.toList());
    List<CompletableFuture<Void>> cfutures =
      tasks.stream()
        .map(CompletableFuture::runAsync)
        .collect(Collectors.toList());
    new Nap(1);
    tasks.forEach(QuittableTask::quit);
    cfutures.forEach(CompletableFuture::join);
  }
}
/* Output:
1 3 4 5 6 2 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23
24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43
44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63
64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83
84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100 101 102
103 104 105 106 107 108 109 110 111 112 113 114 115 116 117
118 119 120 121 122 123 124 7 126 127 128 129 130 131 132
133 134 135 136 137 138 139 140 141 142 143 144 145 146 147
148 149 125
*/
