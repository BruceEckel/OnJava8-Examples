// remote/tests/RMITests.java
// (c)2016 MindView LLC: see Copyright.txt
// We make no guarantees that this code is fit for any purpose.
// Visit http://mindviewinc.com/Books/OnJava/ for more book information.
package remote;

import java.rmi.registry.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInfo;

public class RMITests {
    @Test
    void test_remote_time() throws Exception {
        LocateRegistry.createRegistry(1099);
        PerfectTimeServer timeServer = new PerfectTimeServer();
        timeServer.main(null);
        Registry reg =
                LocateRegistry.getRegistry("localhost");
        PerfectTime pt =
                (PerfectTime) reg.lookup("PerfectTime");
        for (int i = 0; i < 10; i++)
            System.out.println("Time: " + pt.getPerfectTime());
    }
}
