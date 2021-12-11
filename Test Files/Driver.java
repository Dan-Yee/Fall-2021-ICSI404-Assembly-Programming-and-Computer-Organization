import java.lang.Exception;

/**
 * Driver class for TestMemory.java and TestComputer.java
 * @author Dan Yee
 */
public class Driver {
    public static void main(String[] args) throws Exception {
        System.out.println("==========[<<< TEST MEMORY START >>>]<>[<<< TEST MEMORY START >>>]<>[<<< TEST MEMORY START >>>]<>[<<< TEST MEMORY START >>>]<>[<<< TEST MEMORY START >>>]==========");
        TestMemory.runTests();
        System.out.println("==========[<<< TEST MEMORY END >>>]<>[<<< TEST MEMORY END >>>]<>[<<< TEST MEMORY END >>>]<>[<<< TEST MEMORY END >>>]<>[<<< TEST MEMORY END >>>]==========");

        System.out.println("\n==========[<<< TEST COMPUTER START >>>]<>[<<< TEST COMPUTER START >>>]<>[<<< TEST COMPUTER START >>>]<>[<<< TEST COMPUTER START >>>]<>[<<< TEST COMPUTER START >>>]==========");
        TestComputer.runTests();
        System.out.println("\n==========[<<< TEST COMPUTER END >>>]<>[<<< TEST COMPUTER END >>>]<>[<<< TEST COMPUTER END >>>]<>[<<< TEST COMPUTER END >>>]<>[<<< TEST COMPUTER END >>>]==========");
    }
}