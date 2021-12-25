import java.lang.Exception;

public class Computer {
    private boolean isHalted;
    private ALU alu;
    private Memory mainMemory;
    private Longword[] registers;
    private Longword programCounter;
    private Longword instructionRegister;
    private Longword opcode, destinationRegister, operand1, operand2, result;
    private boolean cmpZF, cmpNF;
    private boolean toStore;

    public Computer() {
        this.isHalted = false;
        this.alu = new ALU();
        this.mainMemory = new Memory();

        this.registers = new Longword[16];
        for(int i = 0; i < this.registers.length; i++)
            this.registers[i] = new Longword();

        this.programCounter = new Longword();
        this.programCounter.set(0);

        this.instructionRegister = new Longword();

        this.opcode = new Longword();
        this.destinationRegister = new Longword();
        this.operand1 = new Longword();
        this.operand2 = new Longword();
        this.result = new Longword();
    }

    /**
     * Continually fetches instructions until a halt instruction is received
     * @throws Exception
     */
    public void run() throws Exception {
        while(!isHalted) {
            this.fetch();                                                                                           // fetch next instruction to be decoded
            this.decode();                                                                                          // decode the instruction to be executed
            this.execute();                                                                                         // execute the decoded instruction
            this.store();                                                                                           // store the result of the executed instruction
        }
    }

    /**
     * Reads 2 bytes of data from memory to be decoded and executed
     * @return the 2 bytes of data read stored in a Longword
     * @throws Exception Memory read() method may throw an Exception
     */
    private void fetch() throws Exception {
        Longword numBytes = new Longword();

        numBytes.set(2);                                                                                            // set number of bytes to read to 2
        this.instructionRegister = this.mainMemory.read(this.programCounter, numBytes);             
        this.programCounter.set((this.programCounter.getSigned() + 2));                                             // incremement the program counter by 2 bytes (16 bits) for next instruction fetch
    }

    /**
     * Decodes the data fetched from memory into: instruction operation code, destination register, operand 1, and operand 2
     */
    private void decode() {
        this.opcode = this.instructionRegister.shiftRightLogical(12);                                               // logical right shift to obtain the opcode
        this.destinationRegister = maskLongword(this.instructionRegister.shiftRightLogical(8), 5, 8, 0);            // mask the shifted result to obtain destination register
        this.operand1 = maskLongword(this.instructionRegister.shiftRightLogical(4), 5, 12, 0);                      // mask the shifted result to obtain the first operand
        this.operand2 = maskLongword(this.instructionRegister, 5, 16, 0);                                           // mask the shifted result to obtain the second operand
    }   

    /**
     * Executes the decoded instruction
     * @throws Exception Exception is thrown if the instruction operation code is not recognized
     */
    private void execute() throws Exception {
        this.toStore = false;
        this.result = new Longword();

        if(this.opcode.getBit(3)) {                                                                                 // ALU based operations because MSB of instruction code is 1
            int ALUOpCode = this.opcode.getSigned() % 8;

            this.result = this.alu.operate(ALUOpCode, this.registers[this.operand1.getSigned()], this.registers[this.operand2.getSigned()]);
            this.toStore = true;
        } else {                                                                                                    // Regular operation because MSB of instruction code is 0
            int instructionOpCode = this.opcode.getSigned();

            switch(instructionOpCode) {
                case 0:                                                                                             // Halt (HLT) Instruction
                    this.isHalted = true;
                    break;
                case 1:                                                                                             // Interrupt (INT) Instruction
                    switch(this.operand2.getSigned()) {
                        case 0:                                                                                     // print all registers to screen
                            for(int i = 0; i < this.registers.length; i++)
                                System.out.println("R" + i + ": " + this.registers[i].toString());
                            break;
                        case 1:                                                                                     // print all 256 bytes of memory to screen (4 bytes per line)
                            this.mainMemory.memoryDump();
                            break;
                        default:
                            throw new Exception("ComputerExecuteException: Invalid Interrupt Code");
                    }
                    break;
                case 2:                                                                                             // Move (MOV) Instruction
                    this.toStore = true;

                    for(int i = 0; i < 4; i++) {                                                                    // copy operands 1 and 2 into results
                        this.result.clearBit(i);
                        this.result.clearBit(i + 4);
                        if(this.operand1.getBit(i))
                            this.result.setBit(i + 4);
                        if(this.operand2.getBit(i))
                            this.result.setBit(i);
                    }
                    if(this.result.getBit(7))                                                                       // check for sign extension and mask if needed
                        this.result = maskLongword(this.result, 8, 32, 1);
                    break;
                case 3:                                                                                             // Jump (JMP) Instruction
                    Longword jumpAddress = new Longword();

                    for(int i = 0; i < 12; i++) {                                                                   // copies destination and both operands to be interpreted as jump address
                        if(this.destinationRegister.getBit(i))
                            jumpAddress.setBit(i + 8);
                        if(this.operand1.getBit(i))
                            jumpAddress.setBit(i + 4);
                        if(this.operand2.getBit(i))
                            jumpAddress.setBit(i);
                    }

                    if(jumpAddress.getBit(11))                                                                      // sign extension to preserve negative number
                        jumpAddress = maskLongword(jumpAddress, 12, 32, 1);

                    if((jumpAddress.getSigned() * 2) > 253)
                        throw new Exception("ComputerExecuteException: Jump address out of bounds of memory");
                    else if((jumpAddress.getSigned() * 2) < 0)
                        throw new Exception("ComputerExecuteException: Jump address cannot be less than zero");
                    else
                        this.programCounter.set((jumpAddress.getSigned() * 2));
                    break;
                case 4:                                                                                             // Compare (CMP) Instruction
                    ALU compareALU = new ALU();                                                                     // new ALU specifically for compare operations
                    this.cmpZF = this.cmpNF = true;                                                                 // both flags being true is impossible (used for reset)

                    compareALU.operate(4, this.registers[this.operand1.getSigned()], this.registers[this.operand2.getSigned()]);

                    this.cmpZF = compareALU.getFlag(0);
                    this.cmpNF = compareALU.getFlag(1);
                    break;
                case 5:                                                                                             // Branch (Bxx) Instructions
                    Longword conditionCode = new Longword();
                    Longword branchAddress = new Longword();

                    if(this.destinationRegister.getBit(2))                                                          // copy Branch condition code from destination register
                        conditionCode.setBit(0);
                    if(this.destinationRegister.getBit(3))
                        conditionCode.setBit(1);

                    for(int i = 0; i < 10; i++) {                                                                   // copy address (and sign bit) from destination register and both operands
                        if(this.operand1.getBit(i))
                            branchAddress.setBit(i + 4);
                        if(this.operand2.getBit(i))
                            branchAddress.setBit(i);
                    }
                    if(this.destinationRegister.getBit(0))                                                          // copying last 2 address bits from destination register
                        branchAddress.setBit(8);
                    if(this.destinationRegister.getBit(1)) {
                        branchAddress.setBit(9);
                        branchAddress = maskLongword(branchAddress, 10, 32, 1);                                     // 9th bit is sign bit. if true, perform sign extension to preserve number
                    }

                    if((branchAddress.getSigned() * 2) > 253)
                        throw new Exception("ComputerExecuteException: Jump address out of bounds of memory");
                    else if((branchAddress.getSigned() * 2) < 0)
                        throw new Exception("ComputerExecuteException: Jump address cannot be less than zero");

                    switch(conditionCode.getSigned()) {
                        case 0:                                                                                     // BNE Instruction (Branch if not equal)
                            if(!(this.cmpZF && !this.cmpNF))                                                        // if ZF != 1 and NF != 0
                                this.programCounter.set((branchAddress.getSigned() * 2));
                            break;
                        case 1:                                                                                     // BLT Instruction (Branch if less than)
                            if(!this.cmpZF && this.cmpNF)                                                           // if ZF == 0 and NF == 1 
                                this.programCounter.set((branchAddress.getSigned() * 2));
                            break;
                        case 2:                                                                                     // BEQ Instruction (Branch if equal)
                            if(this.cmpZF && !this.cmpNF)                                                           // if ZF == 1 and NF == 0
                                this.programCounter.set((branchAddress.getSigned() * 2));
                            break;
                        case 3:                                                                                     // BLE Instruction (Branch if less than equal)
                            if((!this.cmpZF && this.cmpNF) || (this.cmpZF && !this.cmpNF))                          // if (ZF == 0 and NF == 1) or (ZF == 1 and NF == 0)
                                this.programCounter.set((branchAddress.getSigned() * 2));
                            break;
                        default:
                            throw new Exception("ComputerExecuteException: Branch condition code not recognized");
                    }
                    break;
                default:
                    throw new Exception("ComputerExecuteException: Machine Op Code Not Recognized");
            }
        }
    }

    /**
     * Store the results of operation after execution of instructions
     */
    private void store() {
        if(toStore)                                                                                                 // only store if needed to prevent stray storing into registers
            this.registers[this.destinationRegister.getSigned()] = new Longword(this.result.getBitVector());
    }

    /**
     * Masks a Longword with either zeros for decode or ones for sign extension
     * @param word the longword to be masked
     * @param start the first bit to be masked
     * @param end the last bit to be masked
     * @param mode mode 0 to mask with 0s, mode 1 to mask with 1s for sign extension
     * @return the masked Longword
     */
    private Longword maskLongword(Longword word, int start, int end, int mode) {
        Longword masked = new Longword();
        masked.copy(word);

        for(int i = start - 1; i < end; i++) {
            masked.clearBit(i);
            if(mode == 1)
                masked.setBit(i);
        }
        return masked;
    }

    /**
     * Preload method for testing instruction sets
     * @param instructions Array of instructions represented as 32 bit binary strings
     * @throws Exception Memory write method may throw an exception
     */
    public void preload(String[] instructions) throws Exception {
        Longword instruction = new Longword();
        Longword address = new Longword();
        Longword numBytes = new Longword();

        address.set(0);
        numBytes.set(2);

        for(int i = 0; i < instructions.length; i++) {
            String instructionStr = instructions[i].replaceAll("\\s+", "");

            for(int j = 0, k = instructionStr.length() - 1; j < instructionStr.length() && k >= 0; j++, k--)
                if(instructionStr.charAt(j) == '1')
                    instruction.setBit(k);
            this.mainMemory.write(address, instruction, numBytes);
            instruction = new Longword();
            address.set((address.getSigned() + 2));
        }
    }
}
