import java.lang.Exception;

/**
 * Implementation of main memory
 * @author Dan Yee
 */
public class Memory {
    private final byte[] memoryArray;

    public Memory() {
        this.memoryArray = new byte[2048];                                  // 256 bytes * 8 bits/byte = 2048 bits

        for(int i = 0; i < 2048; i++)                                       // initialize all memory bits to 0
            this.memoryArray[i] = 0;
    }

    /**
     * Returns the number of bytes available in memory
     * @return the number of bits divided by the size of a byte to get the number of bytes
     */
    public int getMemoryBytes() {
        return this.memoryArray.length / 8;
    }

    /**
     * Accessor to get the memory array (For Testing Purposes Only)
     * @return the byte array representing main memory
     */
    public byte[] getMemoryArray() {
        return this.memoryArray;
    }

    /**
     * Accessor that reads up to 4 bytes in memory starting from a given address
     * @param address Longword representing the initial address of memory to be read
     * @param numBytes Number of bytes to read from memory
     * @return a Longword storing the memory read
     * @throws Exception if address is out of bounds OR if number of bytes if invalid OR Special Case (see below comment)
     */
    public Longword read(Longword address, Longword numBytes) throws Exception {
        Longword returnValue = new Longword();
        int readAmount = numBytes.getSigned();
        int readAddress;

        if(readAmount < 0)
            throw new Exception("MemoryReadException: Number of bytes cannot be less than zero.");
        else if(readAmount > 4)
            throw new Exception("MemoryReadException: Number of bytes cannot be greater than four.");
        else {
            readAddress = address.getSigned();

            if(readAddress < 0)
                throw new Exception("MemoryReadException: Initial read address cannot be less than zero.");
            else if(readAddress > 2048)
                throw new Exception("MemoryReadException: Initial read address cannot be greater than 256 bytes.");
            else if((readAddress + (readAmount * 8)) > 2048)
                // Special Case: Number of bytes to be read, starting from an address, would exceed the maximum size of the memory array.
                throw new Exception("MemoryReadException: Memory read out of bounds for initial address and number of bytes read.");
            else {
                for(int i = readAddress + (readAmount * 8) - 1; i > readAddress; i--) {
                    if(memoryArray[i] == 1)
                        returnValue.setBit(Math.abs((readAmount * 8) - i - 1));
                }
            }
        }
        return returnValue;
    }

    /**
     * Mutator that writes up to 4 bytes in memory starting from a given address
     * @param address Longword representing the initial address of memory to be read
     * @param numBytes Number of bytes to read from memory
     * @return a Longword storing the memory read
     * @throws Exception if address is out of bounds OR if number of bytes if invalid OR Special Case (see below comment)
     */
    public void write(Longword address, Longword word, Longword numBytes) throws Exception {
        int writeAmount = numBytes.getSigned();
        int writeAddress;

        if(writeAmount < 0)
            throw new Exception("MemoryWriteException: Number of bytes cannot be less than zero.");
        else if(writeAmount > 4)
            throw new Exception("MemoryWriteException: Number of bytes cannot be greater than four.");
        else {
            writeAddress = address.getSigned();

            if(writeAddress < 0)
                throw new Exception("MemoryWriteException: Initial write address cannot be less than zero.");
            else if(writeAddress > 2048)
                throw new Exception("MemoryWriteException: Initial write address cannot be greater than 256 bytes");
            else if((writeAddress + (writeAmount * 8)) > 2048)
                // Special Case: Number of bytes to be written, starting from an address, would exceed the maximum size of the memory array.
                throw new Exception("MemoryWriteException: Memory write out of bounds for initial address and number of bytes written.");
            else {
                for(int i = writeAddress; i < writeAddress + (writeAmount * 8); i++) {
                    if(word.getBit(Math.abs((writeAmount * 8) - i - 1)))
                        this.memoryArray[i] = 1;
                }
            }
        }
    }
}