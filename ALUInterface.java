/**
 * Specifications on the implementation of an ALU object
 * @author Dan Yee
 */
public interface ALUInterface {
    /**
     * Accesses the current status of the flag specified by the flagID
     * @param flagID an integer representing one of the four flags of the ALU
     * @return the status of the flag specified by the flagID
     * @throws Exception if flagID is invalid
     */
    public boolean getFlag(int flagID) throws Exception;

    /**
     * Toggles the flag specified by flagID
     * @param flagID a integer representing one of the four flags of the ALU
     * @throws Exception if flagID is invalid
     */
    public void toggleFlag(int flagID) throws Exception;

    /**
     * Operate method that performs an operation based on the ALU code provided
     * @param ALUCode the operation being performed
     * @param operand1 a Longword
     * @param operand2 another Longword
     * @return a new Longword after the operation has been completed
     * @throws Exception if ALU code is invalid
     */
    public Longword operate(int ALUCode, Longword operand1, Longword operand2) throws Exception;
}