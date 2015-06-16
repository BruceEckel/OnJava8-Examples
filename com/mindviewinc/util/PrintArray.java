//: com/mindviewinc/util/PrintArray.java
// ©2015 MindView LLC: see Copyright.txt
// Display an array of double
package com.mindviewinc.util;

public class PrintArray {
  public static void printArray(double[] array) {
    for(int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if(i != array.length -1)
        System.out.print(", ");
    }
    System.out.println();
  }
} ///:~
