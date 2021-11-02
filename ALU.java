import java.lang.Exception;
/**
 * Implementation of an ALU object
 * @author Dan Yee
 */
public class ALU implements ALUInterface {
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
     * Mutator to toggle the flag based on the flagID
     * @param flagID a numerical value representing one of the four flags
     * @throws Exception when the numerical value for flagID isn't recognized
     */
    public void toggleFlag(int flagID) throws Exception {
        switch(flagID) {
            case 0:
                this.zeroFlag = !zeroFlag;
                break;
            case 1:
                this.negativeFlag = !negativeFlag;
                break;
            case 2:
                this.carryOutFlag = !carryOutFlag;
                break;
            case 3:
                this.overflowFlag = !overflowFlag;
                break;
            default:
                throw new Exception("ALU.toggleFlag: Invalid flagID value of " + flagID);
        }
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
        int shiftAmount;
        Longword shiftAmountBitSet = new Longword();

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
                operand2Copy = rippleCarryAdd(operand2Copy.not(), new Longword(), true);                // Get twos complement of second operand because subtraction is the addition of a twos complement number
                returnValue = rippleCarryAdd(operand1Copy, operand2Copy, false);
                break;
            case 5:                                                                                     // Shift Left Logical
                shiftAmount = operand2Copy.getSigned();
                if(shiftAmount < 0) {                                                                   // Shift opposite direction if shift amount is negative
                    shiftAmountBitSet.set(Math.abs(shiftAmount));
                    returnValue = operate(6, operand1Copy, shiftAmountBitSet);
                    break;
                }
                returnValue = operand1Copy.shiftLeftLogical(shiftAmount);

                if(shiftAmount == 1) {                                                                  // check for overflow after SLL operation
                    if(operand1Copy.getBit(31) != returnValue.getBit(31))                               // check for sign flip if shift amount is 1
                        this.toggleFlag(3);
                }
                break;
            case 6:                                                                                     // Shift Right Logical
                shiftAmount = operand2Copy.getSigned();
                if(shiftAmount < 0) {                                                                   // Shift opposite direction if shift amount is negative     
                    shiftAmountBitSet.set(Math.abs(shiftAmount));
                    returnValue = operate(5, operand1Copy, shiftAmountBitSet);
                    break;
                }

                returnValue = operand1Copy.shiftRightLogical(shiftAmount);
                break;
            case 7:                                                                                     // Shift Right Arithmetic
                shiftAmount = operand2Copy.getSigned();
                if(shiftAmount < 0) {                                                                   // Shift opposite direction if shift amount is negative
                    shiftAmountBitSet.set(Math.abs(shiftAmount));
                    returnValue = operate(5, operand1Copy, shiftAmountBitSet);
                    break;
                }
                returnValue = operand1Copy.shiftRightArithmetic(shiftAmount);
                break;
            default:
                throw new Exception("ALU.operate: Invalid ALU Code");
        }
        // Updating ZeroFlag after all ALU operations
        if(returnValue.isZero())
            this.toggleFlag(0);

        // Updating NegativeFlag after ALU operation excluding SRL operation
        if(ALUCode != 6) {
            if(returnValue.getBit(31))
                this.toggleFlag(1);
        }
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
            if(operand1Copy.getBit(i) && operand2Copy.getBit(i)) {
                carryIn = true;
                if(i == 31) {                                                                           // if on last bit and carry out is true
                    this.toggleFlag(2);                                                                 // toggle carry out flag
                    this.toggleFlag(3);                                                                 // toggle overflow flag
                }
            }
        }
        return returnValue;
    }
}