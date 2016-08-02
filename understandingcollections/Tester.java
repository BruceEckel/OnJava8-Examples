// understandingcollections/Tester.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Applies Test objects to lists of different collections
import java.util.*;
import java.time.*;

public class Tester<C> {
  public static int fieldWidth = 8;
  public static
  TestParam[] defaultParams= TestParam.array(
    10, 5000, 100, 5000, 1000, 5000, 10000, 500);
  // Override this to modify pre-test initialization:
  protected C initialize(int size) { return collection; }
  protected C collection;
  private String headline = "";
  private List<Test<C>> tests;
  private static int sizeWidth = 5;
  private static String sizeField = "%" + sizeWidth + "s";
  private TestParam[] paramList = defaultParams;
  public Tester(C collection, List<Test<C>> tests) {
    this.collection = collection;
    this.tests = tests;
    if(collection != null)
      headline = collection.getClass().getSimpleName();
  }
  public Tester(C collection, List<Test<C>> tests,
      TestParam[] paramList) {
    this(collection, tests);
    this.paramList = paramList;
  }
  public void setHeadline(String newHeadline) {
    headline = newHeadline;
  }
  // Generic methods for convenience :
  public static
  <C> void run(C cntnr, List<Test<C>> tests) {
    new Tester<>(cntnr, tests).timedTest();
  }
  public static <C> void run(C cntnr,
      List<Test<C>> tests, TestParam[] paramList) {
    new Tester<>(cntnr, tests, paramList).timedTest();
  }
  private void displayHeader() {
    // Calculate width and pad with '-':
    int width = fieldWidth * tests.size() + sizeWidth;
    int dashLength = width - headline.length() - 1;
    StringBuilder head = new StringBuilder(width);
    for(int i = 0; i < dashLength/2; i++)
      head.append('-');
    head.append(' ');
    head.append(headline);
    head.append(' ');
    for(int i = 0; i < dashLength/2; i++)
      head.append('-');
    System.out.println(head);
    // Print column headers:
    System.out.format(sizeField, "size");
    for(Test<C> test : tests)
      System.out.format(
        "%" + fieldWidth + "s", test.name);
    System.out.println();
  }
  // Run the tests for this collection:
  public void timedTest() {
    displayHeader();
    for(TestParam param : paramList) {
      System.out.format(sizeField, param.size);
      for(Test<C> test : tests) {
        C kontainer = initialize(param.size);
        Instant start = Instant.now();
        // Call the overriden method:
        int reps = test.test(kontainer, param);
        Duration elapsed =
          Duration.between(start, Instant.now());
        Duration timePerRep = elapsed.dividedBy(reps);
        System.out.format("%" + fieldWidth + "d",
          timePerRep.toNanos());
      }
      System.out.println();
    }
  }
}
