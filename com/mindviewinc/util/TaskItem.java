//: com/mindviewinc/util/TaskItem.java
// ©2015 MindView LLC: see Copyright.txt
// A Future and the Callable that produced it.
package com.mindviewinc.util;
import java.util.concurrent.*;

public class TaskItem<R,C extends Callable<R>> {
  public final Future<R> future;
  public final C task;
  public TaskItem(Future<R> future, C task) {
    this.future = future;
    this.task = task;
  }
} ///:~
