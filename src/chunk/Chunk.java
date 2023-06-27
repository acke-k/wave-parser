/**
 * Class for representing a general chunk, as well as helper methods for parsing .wav files.
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.ArrayList;

public abstract class Chunk {
    private String id;
    private Integer size;
    private ArrayList<Chunk> subChunks;
    /**
     * getter for the ID of the chunk. This shows the type of chunk, such as RIFF or DATA
     * @return the id of this chunk
     */
    public String getId() {
        return id;
    }
    /**
     * getter for the size of this chunk.
     * @return the number of bytes in this chunk
     */
    public Integer getSize() {
        return size;
    }
    /**
     * Getter for the subchunks of this chunk
     * @return an array of subchunks
     */
    public Chunk[] getSubChunk() {
        return this.subChunks.toArray(new Chunk[0]);
    }
    /**
     * add a subchunk to this chunk
     * @param subChunk The subchunk to be added
     */
    public void addSubChunk(Chunk subChunk) {
        this.subChunks.add(subChunk);
    }
    /**
     * Constructor for the chunk class.
     */
    public Chunk(String id, Integer size) {
        this.id = id;
        this.size = size;
        this.subChunks = new ArrayList<Chunk>();
    }

    /* Helper functions for parsing chunk option fields */
    /**
     * Converts a byte array representing a hex number to an integer
     * @param arr the array to be converted, must have length <= 8
     * @throws IllegalArgumentException if the number is too large
     * @return the decimal value of thea array
     */
    protected static int hex2dec(byte[] arr) {
        if (arr.length > 4)
            throw new IllegalArgumentException("Expected length <= 4, length is " + arr.length);
        String hexStr = "";
        for (byte b : arr) 
            hexStr += String.format("%02X", b);
        return Integer.parseUnsignedInt(hexStr, 16);
    }

    /**
     * Changes the endian of an integer stored in a byte buffer
     * @param buff A byte buffer containing an integer
     */
    protected static byte[] changeEndian(byte[] buff) {
        byte[] ret = new byte[buff.length];
        int i = 0;
        int j = buff.length - 1;
        while (i < j) {
            ret[i] = buff[j];
            ret[j] = buff[i];
            i++;
            j--;
        }
        return ret;
    }

}
