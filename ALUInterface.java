/**
 * Specifications on the implementation of an ALU object
 * @author Dan Yee
 */
public interface ALUInterface {
    /**
     * Accessor that returns the status of the Zero Flag
     * @return a boolean representing the status of the Zero Flag
     */
    public boolean getZeroFlag();

    /**
     * Accessor that gets the status of the Negative Flag
     * @return a boolean representing the status of the Negative Flag
     */
    public boolean getNegativeFlag();

    /**
     * Accessor that gets the status of the Carry Out Flag
     * @return a boolean representing the status of the Carry Out Flag
     */
    public boolean getCarryOutFlag();

    /**
     * Accessor that gets the status of the Overflow Flag
     * @return a boolean representing the status of the Overflow Flag
     */
    public boolean getOverflowFlag();

    /**
     * Toggles the flag specified by flagID
     * @param flagID a integer representing one of the four flags of the ALU
     */
    public void toggleFlag(int flagID) throws Exception;

    /**
     * Operate method that performs an operation based on the ALU code provided
     * @param ALUCode the operation being performed
     * @param operator1 a Longword
     * @param operator2 another Longword
     * @return a new Longword after the operation has been completed
     */
    public Longword operate(int ALUCode, Longword operand1, Longword operand2) throws Exception;
}