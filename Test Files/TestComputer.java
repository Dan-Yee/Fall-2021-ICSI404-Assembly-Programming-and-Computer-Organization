import java.lang.Exception;

public class TestComputer {
    public static void runTests() throws Exception {
        Computer cpu = new Computer();
        String[] preloaded = new String[]{"0011 0000 0000 0011", "0000 0000 0000 0000",
                                        "0000 0000 0000 0000", "0010 0001 0000 1010",
                                        "0001 0000 0000 0000", "0001 0000 0000 0001",
                                        "0010 0011 0000 1111", "0010 0100 0001 0100",
                                        "0100 0000 0011 0100", "0101 0000 0000 1011",
                                        "0000 0000 0000 0000", "0001 0000 0000 0001",
                                        "0100 0000 0011 0100", "0101 0100 0000 1111",
                                        "0000 0000 0000 0000", "0100 0000 0011 0100",
                                        "0101 1100 0001 0100", "0000 0000 0000 0000",
                                        "0000 0000 0000 0000", "0000 0000 0000 0000",
                                        "0010 0101 0100 1011", "0010 0110 0100 1011",
                                        "0100 0000 0101 0110", "0101 1000 0001 1001",
                                        "0000 0000 0000 0000", "0010 0111 0000 1111",
                                        "0010 1000 0001 0001", "1000 1001 0111 1000",
                                        "0001 0000 0000 0000", "1001 1010 0111 1000",
                                        "1010 1011 0111 1000", "1011 1100 0111 1000",
                                        "1100 1101 0111 1000", "0010 0010 0000 1111",
                                        "1101 0000 0010 0001", "0010 0010 1111 1111",
                                        "0010 0001 0000 0010", "1110 1110 0010 0001",
                                        "1111 1111 0010 0001", "0001 0000 0000 0000",
                                        "0001 0000 0000 0001", "0000 0000 0000 0000"};
        
        cpu.preload(preloaded);
        cpu.run();

        // Display all instructions being tested
        System.out.println("\n--------------------< Instructions Tested in Chronological Order >--------------------");
        System.out.println("--------------------< Output Displayed Above (for applicable instructions) >--------------------");
        System.out.println("JMP 3");            // address 0
        System.out.println("MOV R1 10");        // address 6
        System.out.println("INT 0");            // address 8
        System.out.println("INT 1");            // address 10
        System.out.println("MOV R3 15");        // address 12
        System.out.println("MOV R4 20");        // address 14
        System.out.println("CMP R3 R4");        // address 16
        System.out.println("BNE 11");           // address 18
        System.out.println("INT 1");            // address 22
        System.out.println("CMP R3 R4");        // address 24
        System.out.println("BLT 15");           // address 26
        System.out.println("CMP R3 R4");        // address 30
        System.out.println("BLE 20");           // address 32
        System.out.println("MOV R5 75");        // address 40
        System.out.println("MOV R6 75");        // address 42
        System.out.println("CMP R5 R6");        // address 44
        System.out.println("BEQ 25");           // address 46
        System.out.println("MOV R7 15");        // address 50
        System.out.println("MOV R8 17");        // address 52
        System.out.println("AND R9 R7 R8");     // address 54
        System.out.println("INT 0");            // address 56
        System.out.println("OR R10 R7 R8");     // address 58
        System.out.println("XOR R11 R7 R8");    // address 60
        System.out.println("ADD R12 R7 R8");    // address 62
        System.out.println("SUB R13 R7 R8");    // address 64
        System.out.println("MOV R2 15");        // address 66
        System.out.println("SLL R0 R2 R1");     // address 68
        System.out.println("MOV R2 -1");        // address 70
        System.out.println("MOV R1 2");         // address 72
        System.out.println("SRL R14 R2 R1");    // address 74
        System.out.println("SRA R15 R2 R1");    // address 76 
        System.out.println("INT 0");            // address 78
        System.out.println("INT 1");            // address 80
        System.out.println("HLT");              // address 82
        System.out.println("--------------------< Output Displayed Above (for applicable instructions) >--------------------");
        System.out.println("--------------------< Expected Results of Registers after HLT >-------------------------");
        System.out.println("\tR0: 0000 0000 0000 0000 0011 1100 0000 0000     0x00003C00");
        System.out.println("\tR1: 0000 0000 0000 0000 0000 0000 0000 0010     0x00000002");
        System.out.println("\tR2: 1111 1111 1111 1111 1111 1111 1111 1111     0xFFFFFFFF");
        System.out.println("\tR3: 0000 0000 0000 0000 0000 0000 0000 1111     0x0000000F");
        System.out.println("\tR4: 0000 0000 0000 0000 0000 0000 0001 0100     0x00000014");
        System.out.println("\tR5: 0000 0000 0000 0000 0000 0000 0100 1011     0x0000004B");
        System.out.println("\tR6: 0000 0000 0000 0000 0000 0000 0100 1011     0x0000004B");
        System.out.println("\tR7: 0000 0000 0000 0000 0000 0000 0000 1111     0x0000000F");
        System.out.println("\tR8: 0000 0000 0000 0000 0000 0000 0001 0001     0x00000011");
        System.out.println("\tR9: 0000 0000 0000 0000 0000 0000 0000 0001     0x00000001");
        System.out.println("\tR10: 0000 0000 0000 0000 0000 0000 0001 1111    0x0000001F");
        System.out.println("\tR11: 0000 0000 0000 0000 0000 0000 0001 1110    0x0000001E");
        System.out.println("\tR12: 0000 0000 0000 0000 0000 0000 0010 0000    0x00000020");
        System.out.println("\tR13: 1111 1111 1111 1111 1111 1111 1111 1110    0xFFFFFFFE");
        System.out.println("\tR14: 0011 1111 1111 1111 1111 1111 1111 1111    0x3FFFFFFF");
        System.out.println("\tR15: 1111 1111 1111 1111 1111 1111 1111 1111    0xFFFFFFFF");
        System.out.println("--------------------< Output Displayed Above (for applicable instructions) >--------------------");
    }
}