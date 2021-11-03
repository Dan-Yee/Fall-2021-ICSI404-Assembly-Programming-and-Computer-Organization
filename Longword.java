import java.util.BitSet;

/**
 * Implementation of a Longword class using Java BitSet class
 * @author Dan Yee
 */
public class Longword {
    private BitSet bitVector;

    public Longword() {
        this.bitVector = new BitSet(32);
    }

    // Overloading constructor for returning new Longwords with a specified BitSet
    public Longword(BitSet bitSet) {
        this.bitVector = new BitSet(32);

        for(int i = 0; i < 32; i++) {
            this.bitVector.set(i, bitSet.get(i));
        }
    }

    /**
     * Returns a boolean representing the status of a bit at a given index
     * @param index - index of the bit wanted
     * @return true(1)/false(0) based on the status of the bit
     */
    public boolean getBit(int index) {
        return this.bitVector.get(index);
    }

    /**
     * Sets the bit at the specified index to true
     * @param index - index of the bit to be changed
     */
    public void setBit(int index) {
        this.bitVector.set(index);
    }

    /**
     * Sets the bit at the specified index to false
     * @param index - index of the bit to be changed
     */
    public void clearBit(int index) {
        this.bitVector.clear(index);
    }

    /**
     * Changes the bit at the specified index to be its complement
     * @param index - index of the bit to be changed
     */
    public void toggleBit(int index) {
        this.bitVector.flip(index);
    }

    /**
     * Gets the signed value of the bit representation using twos complement if necessary
     * @return a signed int of the bit representation
     */
    public int getSigned() {
        int signedValue = 0;
        Longword longwordCopy = new Longword(this.getBitVector());

        for(int i = 0; i < 31; i++) {
            if(longwordCopy.getBit(i))
                signedValue += Math.pow(2, i);
        }
        if(longwordCopy.getBit(31))                                                         // if MSB is 1, subtract Integer Min Value to account for negative number
            signedValue -= Integer.MIN_VALUE;

        return signedValue;
    }

    /**
     * Gets the unsigned value of the bit representation
     * @return a unsigned long of the bit representation
     */
    public long getUnsigned() {
        long unsignedValue = 0;

        for(int i = 0; i < 32; i++) {
            if(this.getBit(i))
                unsignedValue += Math.pow(2, i);
        }
        return unsignedValue;
    }

    /**
     * Converts a int to binary and sets each bit respectively to represent the number in binary
     * @param value the value being set for this longword
     */
    public void set(int value) {
        String binaryRepresentation = String.format("%32s", Integer.toBinaryString(value)).replaceAll(" ", "0");
        this.bitVector.clear();

        for(int i = binaryRepresentation.length() - 1; i >= 0; i--) {
            if(binaryRepresentation.charAt(i) == '1')
                this.setBit(Math.abs(i - 31));
        }
    }

    /**
     * Copies the bits of a different Longword into this one
     * @param other - a reference to the other longword
     */
    public void copy(Longword other) {
        for(int i = 0; i < 32; i++) {
            this.bitVector.set(i, other.getBit(i));
        }
    }

    /**
     * Accessor to get the Bit Vector of a Longword object
     * @return this.bitVector - a reference to the BitSet object of the Longword
     */
    public BitSet getBitVector() {
        return this.bitVector;
    }

    /**
     * Logically left shifts the longword by nAmount
     * @param nAmount amount to be left shifted
     * @return a new Longword after it was logically right shifted
     */
    public Longword shiftLeftLogical(int nAmount) {       
        BitSet shiftedSet = new BitSet(32);

        for(int i = nAmount; i < 32; i++) {
            shiftedSet.set(i, this.getBit(i - nAmount));
        }
        return new Longword(shiftedSet);
    }

    /**
     * Logically right shifts the longword by nAmount
     * @param nAmount amount to be right shifted
     * @return a new Longword after it was logically right shifted
     */
    public Longword shiftRightLogical(int nAmount) {
        BitSet shiftedSet = new BitSet(32);
        BitSet copyThis = new BitSet(32);

        for(int i = 0; i < 32; i++) {
            copyThis.set(i, this.getBit(i));
        }

        while(nAmount > 0) {
            for(int i = 0; i < 32; i++) {
                shiftedSet.set(i, copyThis.get(i + 1));
            }
            copyThis = copyThis.get(1, 32);
            nAmount--;
        }
        return new Longword(shiftedSet);
    }

    /**
     * Right shifts the long word by nAmounts and preserves the most significant bit
     * @param nAmount amount to be right shifted
     * @return a new Longword after it has been arithmetically right shifted
     */
    public Longword shiftRightArithmetic(int nAmount) {
        Longword shiftedLongword = this.shiftRightLogical(nAmount);
        boolean MSB = this.getBit(31);

        for(int i = 31 - nAmount; i < 32; i++) {
            if(MSB) {
                shiftedLongword.clearBit(i);
                shiftedLongword.setBit(i);
            } else
                shiftedLongword.clearBit(i);
        }

        return new Longword(shiftedLongword.getBitVector());
    }
    
    /**
     * Negates each bit on the longword and returns a new longword
     * @return new Longword(longwordCopy.getBitVector()) - a new longword based on not operator on original longword
     */
    public Longword not() {
        Longword longwordCopy = new Longword(this.getBitVector());

        for(int i = 0; i < 32; i++) {
            longwordCopy.toggleBit(i);
        }
        return new Longword(longwordCopy.getBitVector());
    }

    /**
     * Performs logical AND on two BitSets's and returns a new Longword based on the copied target BitSet
     * @param other - a reference to the argument BitSet
     * @return new Longword(longwordCopy) - a new Longword based on the copied target BitSet
     */
    public Longword and(Longword other) {
        BitSet bitVectorCopy = new BitSet(32);

        for(int i = 0; i < 32; i++) {
            bitVectorCopy.set(i, this.bitVector.get(i));
        }
        bitVectorCopy.and(other.getBitVector());
        return new Longword(bitVectorCopy);
    }

    /**
     * Performs logical OR on two BitSet's and returns a new Longword based on the copied target BitSet
     * @param other - a reference to the argument BitSet
     * @return new Longword(bitVectorCopy) - a new Longword based on the copied target BitSet
     */
    public Longword or(Longword other) {
        BitSet bitVectorCopy = new BitSet(32);

        for(int i = 0; i < 32; i++) {
            bitVectorCopy.set(i, this.bitVector.get(i));
        }
        bitVectorCopy.or(other.getBitVector());
        return new Longword(bitVectorCopy);
    }

    /**
     * Performs logical XOR on two BitSet's and returns a new Longword based on the copied target BitSet
     * @param other - a reference to the argument BitSet
     * @return new Longword(bitVectorCopy) - a new Longword based on the copied target BitSet
     */
    public Longword xor(Longword other) {
        BitSet bitVectorCopy = new BitSet(32);

        for(int i = 0; i < 32; i++) {
            bitVectorCopy.set(i, this.bitVector.get(i));
        }
        bitVectorCopy.xor(other.getBitVector());
        return new Longword(bitVectorCopy);
    }

    /**
     * Checks each bit to see if they are all zero
     * @return false if any one bit is not zero
     * @return true if all bits are zero
     */
    public boolean isZero() {
        for(int i = 0; i < 32; i++) {
            if(this.getBit(i) == true)
                return false;
            continue;
        }
        return true;
    }

    /**
     * Overrides default toString method to display Longword as 32 bits and equivalent hexadecimal format
     * @return a String displaying the Longword and its hexadecimal equivalent
     */
    @Override
    public String toString() {
        String tempStr = "";
        int bitCount = 0;
        String binaryRepresentation = "";
        String hexRepresentation = "";

        for(int i = 0; i < 32; i++) {
            if(this.getBit(i))
                tempStr = "1" + tempStr;
            else
                tempStr = "0" + tempStr;
            bitCount++;

            if(bitCount == 4) {
                switch(tempStr) {
                    case "0000":
                        hexRepresentation = "0" + hexRepresentation;
                        break;
                    case "0001":
                        hexRepresentation = "1" + hexRepresentation;
                        break;
                    case "0010":
                        hexRepresentation = "2" + hexRepresentation;
                        break;
                    case "0011":
                        hexRepresentation = "3" + hexRepresentation;
                        break;
                    case "0100":
                        hexRepresentation = "4" + hexRepresentation;
                        break;
                    case "0101":
                        hexRepresentation = "5" + hexRepresentation;
                        break;
                    case "0110":
                        hexRepresentation = "6" + hexRepresentation;
                        break;
                    case "0111":
                        hexRepresentation = "7" + hexRepresentation;
                        break;
                    case "1000":
                        hexRepresentation = "8" + hexRepresentation;
                        break;
                    case "1001":
                        hexRepresentation = "9" + hexRepresentation;
                        break;
                    case "1010":
                        hexRepresentation = "A" + hexRepresentation;
                        break;
                    case "1011":
                        hexRepresentation = "B" + hexRepresentation;
                        break;
                    case "1100":
                        hexRepresentation = "C" + hexRepresentation;
                        break;
                    case "1101":
                        hexRepresentation = "D" + hexRepresentation;
                        break;
                    case "1110":
                        hexRepresentation = "E" + hexRepresentation;
                        break;
                    case "1111":
                        hexRepresentation = "F" + hexRepresentation;
                        break;
                    default:
                        hexRepresentation = "?" + hexRepresentation;
                        break;
                }
                binaryRepresentation = tempStr + " " + binaryRepresentation;
                bitCount = 0;
                tempStr = "";
            }
        }
        return binaryRepresentation + "\t0x" + hexRepresentation;
    }
}