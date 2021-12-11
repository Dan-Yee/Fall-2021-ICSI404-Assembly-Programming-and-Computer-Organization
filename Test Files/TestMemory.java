import java.lang.Exception;

/**
 * Testing Memory.java Class Methods
 * @author Dan Yee
 */
public class TestMemory {
    public static void runTests() throws Exception {
        Memory mem = new Memory();
        Longword address = new Longword();
        Longword numBytes = new Longword();
        Longword word = new Longword();
        Longword readResult;


        System.out.println("--------------------------------------------------< Memory Test 1 >--------------------------------------------------");
        word.set(15);
        address.set(0);
        numBytes.set(1);

        System.out.println("Memory Write Test: (using Big Endian Convention)");
        System.out.println("Written: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Longword String: " + word.toString());
        System.out.println("- Longword Value: " + word.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        mem.write(address, word, numBytes);

        System.out.println("\nMemory Read Test: ");
        System.out.println("Read: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        readResult = mem.read(address, numBytes);

        System.out.println("Read Results: " + readResult.toString());
        System.out.println("Read Results Value: " + readResult.getSigned());

        System.out.println("\nRead Results are equal to Write Results: " + (readResult.toString()).equals(word.toString()));

        System.out.println("--------------------------------------------------< Memory Test 2 >--------------------------------------------------");
        mem = new Memory();
        word.set(12345);
        address.set(0);
        numBytes.set(4);

        System.out.println("Memory Write Test: (using Big Endian Convention)");
        System.out.println("Written: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Longword String: " + word.toString());
        System.out.println("- Longword Value: " + word.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        mem.write(address, word, numBytes);

        System.out.println("\nMemory Read Test: ");
        System.out.println("Read: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        readResult = mem.read(address, numBytes);

        System.out.println("Read Results: " + readResult.toString());
        System.out.println("Read Results Value: " + readResult.getSigned());

        System.out.println("\nRead Results are equal to Write Results: " + (readResult.toString()).equals(word.toString()));

        System.out.println("\n--------------------------------------------------< Memory Test 3 >--------------------------------------------------");
        mem = new Memory();
        word.set(Integer.MAX_VALUE);
        address.set(70);
        numBytes.set(4);

        System.out.println("Memory Write Test: (using Big Endian Convention)");
        System.out.println("Written: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Longword String: " + word.toString());
        System.out.println("- Longword Value: " + word.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        mem.write(address, word, numBytes);

        System.out.println("\nMemory Read Test: ");
        System.out.println("Read: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        readResult = mem.read(address, numBytes);

        System.out.println("Read Results: " + readResult.toString());
        System.out.println("Read Results Value: " + readResult.getSigned());

        System.out.println("\nRead Results are equal to Write Results: " + (readResult.toString()).equals(word.toString()));

        System.out.println("\n--------------------------------------------------< Memory Test 4 >--------------------------------------------------");
        mem = new Memory();
        word.set(Integer.MIN_VALUE);
        address.set(56);
        numBytes.set(4);

        System.out.println("Memory Write Test: (using Big Endian Convention)");
        System.out.println("Written: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Longword String: " + word.toString());
        System.out.println("- Longword Value: " + word.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        mem.write(address, word, numBytes);

        System.out.println("\nMemory Read Test: ");
        System.out.println("Read: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        readResult = mem.read(address, numBytes);

        System.out.println("Read Results: " + readResult.toString());
        System.out.println("Read Results Value: " + readResult.getSigned());

        System.out.println("\nRead Results are equal to Write Results: " + (readResult.toString()).equals(word.toString()));

        System.out.println("\n--------------------------------------------------< Memory Test 5 >--------------------------------------------------");
        mem = new Memory();
        word.set(-6516515);
        address.set(100);
        numBytes.set(4);

        System.out.println("Memory Write Test: (using Big Endian Convention)");
        System.out.println("Written: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Longword String: " + word.toString());
        System.out.println("- Longword Value: " + word.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        mem.write(address, word, numBytes);

        System.out.println("\nMemory Read Test: ");
        System.out.println("Read: ");
        System.out.println("- Initial Address: " + address.getSigned());
        System.out.println("- Number of Bytes: " + numBytes.getSigned());
        readResult = mem.read(address, numBytes);

        System.out.println("Read Results: " + readResult.toString());
        System.out.println("Read Results Value: " + readResult.getSigned());

        System.out.println("\nRead Results are equal to Write Results: " + (readResult.toString()).equals(word.toString()));
    }
}