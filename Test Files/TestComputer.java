import java.lang.Exception;

public class TestComputer {
    public static void runTests() throws Exception {
        Computer cpu = new Computer();
        Memory mainMem = cpu.getMainMemory();
        Longword instruction = new Longword();
        Longword numBytes = new Longword();
        Longword address = new Longword();

        // JMP 3
        // 0011 0000 0000 0011
        instruction.set(12291);
        numBytes.set(2);
        address.set(0);
        mainMem.write(address, instruction, numBytes);
        
        // MOV R1 10
        // 0010 0001 0000 1010
        instruction.set(8458);
        numBytes.set(2);
        address.set(6);
        mainMem.write(address, instruction, numBytes);

        // INT 0
        // 0001 0000 0000 0000
        instruction.set(4096);
        numBytes.set(2);
        address.set(8);
        mainMem.write(address, instruction, numBytes);

        // INT 1
        // 0001 0000 0000 0000
        instruction.set(4097);
        numBytes.set(2);
        address.set(10);
        mainMem.write(address, instruction, numBytes);

        // MOV R3 15
        // 0010 0011 0000 1111
        instruction.set(8975);
        numBytes.set(2);
        address.set(12);
        mainMem.write(address, instruction, numBytes);

        // MOV R4 20
        // 0010 0100 0001 0100
        instruction.set(9236);
        numBytes.set(2);
        address.set(14);
        mainMem.write(address, instruction, numBytes);
        
        // CMP R3 R4
        // 0100 0000 0011 0100
        instruction.set(16436);
        numBytes.set(2);
        address.set(16);
        mainMem.write(address, instruction, numBytes);

        // BNE 11
        // 0101 0000 0000 1011
        instruction.set(20491);
        numBytes.set(2);
        address.set(18);
        mainMem.write(address, instruction, numBytes);

        // INT 1
        // 0001 0000 0000 0001
        instruction.set(4097);
        numBytes.set(2);
        address.set(22);
        mainMem.write(address, instruction, numBytes);

        // CMP R3 R4
        // 0100 0000 0011 0100
        instruction.set(16436);
        numBytes.set(2);
        address.set(24);
        mainMem.write(address, instruction, numBytes);

        // BLT 15
        // 0101 0100 0000 1111
        instruction.set(21519);
        numBytes.set(2);
        address.set(26);
        mainMem.write(address, instruction, numBytes);

        // CMP R3 R4
        // 0100 0000 0011 0100
        instruction.set(16436);
        numBytes.set(2);
        address.set(30);
        mainMem.write(address, instruction, numBytes);

        // BLE 20
        // 0101 1100 0001 0100
        instruction.set(23572);
        numBytes.set(2);
        address.set(32);
        mainMem.write(address, instruction, numBytes);

        // MOV R5 75
        // 0010 0101 0100 1011
        instruction.set(9547);
        numBytes.set(2);
        address.set(40);
        mainMem.write(address, instruction, numBytes);

        // MOV R6 75
        // 0010 0110 0100 1011
        instruction.set(9803);
        numBytes.set(2);
        address.set(42);
        mainMem.write(address, instruction, numBytes);

        // CMP R5 R6
        // 0100 0000 0101 0110
        instruction.set(16470);
        numBytes.set(2);
        address.set(44);
        mainMem.write(address, instruction, numBytes);

        // BEQ 25
        // 0101 1000 0001 1001
        instruction.set(22553);
        numBytes.set(2);
        address.set(46);
        mainMem.write(address, instruction, numBytes);

        // MOV R7 15
        // 0010 0111 0000 1111
        instruction.set(9999);
        numBytes.set(2);
        address.set(50);
        mainMem.write(address, instruction, numBytes);

        // MOV R8 17
        // 0010 1000 0001 0001
        instruction.set(10257);
        numBytes.set(2);
        address.set(52);
        mainMem.write(address, instruction, numBytes);

        // AND R9 R7 R8
        // 1000 1001 0111 1000
        instruction.set(-30344);
        numBytes.set(2);
        address.set(54);
        mainMem.write(address, instruction, numBytes);

        // INT 0
        // 0001 0000 0000 0000
        instruction.set(4096);
        numBytes.set(2);
        address.set(56);
        mainMem.write(address, instruction, numBytes);
        
        // OR R10 R7 R8
        // 1001 1010 0111 1000
        instruction.set(-25992);
        numBytes.set(2);
        address.set(58);
        mainMem.write(address, instruction, numBytes);

        // XOR R11 R7 R8
        // 1010 1011 0111 1000
        instruction.set(-21640);
        numBytes.set(2);
        address.set(60);
        mainMem.write(address, instruction, numBytes);

        // ADD R12 R7 R8
        // 1011 1100 0111 1000
        instruction.set(-17288);
        numBytes.set(2);
        address.set(62);
        mainMem.write(address, instruction, numBytes);

        // SUB R13 R7 R8
        // 1100 1101 0111 1000
        instruction.set(-12936);
        numBytes.set(2);
        address.set(64);
        mainMem.write(address, instruction, numBytes);

        // MOV R2 15
        // 0010 0010 0000 1111
        instruction.set(8719);
        numBytes.set(2);
        address.set(66);
        mainMem.write(address, instruction, numBytes);

        // SLL R0 R2 R1
        // 1101 0000 0010 0001
        instruction.set(-12255);
        numBytes.set(2);
        address.set(68);
        mainMem.write(address, instruction, numBytes);

        // MOV R2 -1
        // 0010 0010 1111 1111
        instruction.set(8959);
        numBytes.set(2);
        address.set(70);
        mainMem.write(address, instruction, numBytes);

        // MOV R1 2
        // 0010 0001 0000 0010
        instruction.set(8450);
        numBytes.set(2);
        address.set(72);
        mainMem.write(address, instruction, numBytes);

        // SRL R14 R2 R1
        // 1110 1110 0010 0001
        instruction.set(-4575);
        numBytes.set(2);
        address.set(74);
        mainMem.write(address, instruction, numBytes);

        // SRA R15 R2 R1
        // 1111 1111 0010 0001
        instruction.set(-223);
        numBytes.set(2);
        address.set(76);
        mainMem.write(address, instruction, numBytes);

        // INT 0
        // 0001 0000 0000 0000
        instruction.set(4096);
        numBytes.set(2);
        address.set(78);
        mainMem.write(address, instruction, numBytes);

        // INT 1
        // 0001 0000 0000 0001
        instruction.set(4097);
        numBytes.set(2);
        address.set(80);
        mainMem.write(address, instruction, numBytes);

        // HLT
        // 0000 0000 0000 0000
        instruction.set(0);
        numBytes.set(2);
        address.set(82);
        mainMem.write(address, instruction, numBytes);

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