// remote/DisplayPerfectTime.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
// Uses remote object PerfectTime
// {ValidateByHand}
import java.rmi.registry.*;

public class DisplayPerfectTime {
  public static void
  main(String[] args) throws Exception {
    Registry reg =
      LocateRegistry.getRegistry("localhost");
    PerfectTime pt =
      (PerfectTime)reg.lookup("PerfectTime");
    for(int i = 0; i < 10; i++)
      System.out.println("Time: "+ pt.getPerfectTime());
  }
}
