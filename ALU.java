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
     * Accessor to get the status of the Zero Flag
     * @return a boolean representing the status of the Zero Flag
     */
    public boolean getZeroFlag() {
        return this.zeroFlag;
    }

    /**
     * Accessor to get the status of the Negative Flag
     * @return a boolean representing the status of the Negative Flag
     */
    public boolean getNegativeFlag() {
        return this.negativeFlag;
    }

    /**
     * Accessor to get the status of the Carry Out Flag
     * @return a boolean representing the status of the Carry Out Flag
     */
    public boolean getCarryOutFlag() {
        return this.carryOutFlag;
    }

    /**
     * Accessor to get the status of the Overflow Flag
     * @return a boolean representing the status of the Overflow Flag
     */
    public boolean getOverflowFlag() {
        return this.overflowFlag;
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
            default:
                throw new Exception("ALU.toggleFlag.30: Invalid flagID");
        }
    }

    /**
     * Method that performs an operation on two Longword's based on the ALU operation code
     * @return a new Longword after the ALU operation has been performed
     * @throws Exception if the ALU code is not recognized
     */
    public Longword operate(int ALUCode, Longword operand1, Longword operand2) throws Exception {
        switch(ALUCode) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                throw new Exception("ALU.operate.48: Invalid ALU Code");
        }

        return new Longword();
    }
}