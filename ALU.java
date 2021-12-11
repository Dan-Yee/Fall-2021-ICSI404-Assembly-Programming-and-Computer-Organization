import java.lang.Exception;
/**
 * Implementation of an ALU object
 * @author Dan Yee
 */
public class ALU {
    private boolean zeroFlag;
    private boolean negativeFlag;
    private boolean carryOutFlag;
    private boolean overflowFlag;

    public ALU() {
        this.zeroFlag = false;
        this.negativeFlag = false;
        this.carryOutFlag = false;
        this.overflowFlag = false;
    }

    /**
     * Accesses the current status of the flag specified by the flagID
     * @param flagID an integer representing one of the four flags of the ALU
     * @return the status of the flag specified by the flagID
     * @throws Exception if flagID is invalid
     */
    public boolean getFlag(int flagID) throws Exception {
        switch(flagID) {
            case 0:
                return this.zeroFlag;
            case 1:
                return this.negativeFlag;
            case 2:
                return this.carryOutFlag;
            case 3:
                return this.overflowFlag;
            default:
                throw new Exception("ALU.getFlag: Invalid flagID value of " + flagID);
        }
    }

    /**
     * Mutator to change the status of the Zero Flag
     * @param flagStatus the new status of the flag
     */
    public void setZeroFlag(boolean flagStatus) {
        this.zeroFlag = flagStatus;
    }

    /**
     * Mutator to change the status of the Negative Flag
     * @param flagStatus the new status of the flag
     */
    public void setNegativeFlag(boolean flagStatus) {
        this.negativeFlag = flagStatus;
    }

    /**
     * Mutator to change the status of the Carry Out Flag
     * @param flagStatus the new status of the flag
     */
    public void setCarryOutFlag(boolean flagStatus) {
        this.carryOutFlag = flagStatus;
    }

    /**
     * Mutator to change the status of the Overflow Flag
     * @param flagStatus the new status of the flag
     */
    public void setOverflowFlag(boolean flagStatus) {
        this.overflowFlag = flagStatus;
    }

    /**
     * Method that performs an operation on two Longword's based on the ALU operation code
     * @return a new Longword after the ALU operation has been performed
     * @throws Exception if the ALU code is not recognized
     */
    public Longword operate(int ALUCode, Longword operand1, Longword operand2) throws Exception {
        Longword operand1Copy = new Longword(operand1.getBitVector());
        Longword operand2Copy = new Longword(operand2.getBitVector());
        Longword returnValue = new Longword();
        long shiftAmount;

        switch(ALUCode) {
            case 0:                                                                                     // Logical AND
                returnValue = operand1Copy.and(operand2Copy);
                break;
            case 1:                                                                                     // Logical OR
                returnValue = operand1Copy.or(operand2Copy);
                break;
            case 2:                                                                                     // Logical XOR
                returnValue = operand1Copy.xor(operand2Copy);
                break;
            case 3:                                                                                     // Addition
                returnValue = rippleCarryAdd(operand1Copy, operand2Copy, false);
                break;
            case 4:                                                                                     // Subtraction
                returnValue = rippleCarryAdd(operand1Copy, operand2Copy.not(), true);
                break;
            case 5:                                                                                     // Shift Left Logical
                shiftAmount = operand2Copy.getUnsigned();
                if(shiftAmount > 31)
                    shiftAmount %= 32;
                returnValue = operand1Copy.shiftLeftLogical((int)shiftAmount);

                if(shiftAmount == 1) {                                                                  // check for overflow after SLL operation
                    if(operand1Copy.getBit(31) != returnValue.getBit(31))                               // check for sign flip if shift amount is 1
                        this.setOverflowFlag(true);
                }
                break;
            case 6:                                                                                     // Shift Right Logical
                shiftAmount = operand2Copy.getUnsigned();
                if(shiftAmount > 31)
                    shiftAmount %= 32;
                returnValue = operand1Copy.shiftRightLogical((int)shiftAmount);
                break;
            case 7:                                                                                     // Shift Right Arithmetic
                shiftAmount = operand2Copy.getUnsigned();
                if(shiftAmount > 31)
                    shiftAmount %= 32;
                returnValue = operand1Copy.shiftRightArithmetic((int)shiftAmount);
                break;
            default:
                throw new Exception("ALU.operate: Invalid ALU Code");
        }
        // Updating ZeroFlag after all ALU operations
        this.setZeroFlag(returnValue.isZero());

        // Updating NegativeFlag after ALU operation excluding SRL operation
        if(ALUCode != 6)
            this.setNegativeFlag(returnValue.getBit(31));
        return returnValue;
    }

    private Longword rippleCarryAdd(Longword operand1, Longword operand2, boolean carryIn) throws Exception {
        Longword operand1Copy = new Longword(operand1.getBitVector());
        Longword operand2Copy = new Longword(operand2.getBitVector());
        Longword returnValue = new Longword();
        boolean result;

        for(int i = 0; i < 32; i++) {
            result = (operand1Copy.getBit(i) ^ operand2Copy.getBit(i)) ^ carryIn;
            if(result) {
                returnValue.toggleBit(i);
                carryIn = false;
            }
            if(operand1Copy.getBit(i) && operand2Copy.getBit(i) && !carryIn)                            // check for carry for next bit
                carryIn = true;
            if(i == 31)                                                                                 // if on last bit set carry out flag
                this.setCarryOutFlag(carryIn);
        }

        // check for overflow and set the flag accordingly
        if(operand1Copy.getBit(31) == false && operand2Copy.getBit(31) == false) {
            if(returnValue.getBit(31))
                this.setOverflowFlag(true);
        } else if(operand1Copy.getBit(31) && operand2Copy.getBit(31)) {
            if(returnValue.getBit(31) == false)
                this.setOverflowFlag(true);
        }
        return returnValue;
    }
}