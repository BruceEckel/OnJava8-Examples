// remote/DisplayPerfectTime.java
// ©2015 MindView LLC: see Copyright.txt
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
