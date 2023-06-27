/**
 * Class for parsing and storing a Data chunk.
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.Arrays;

public class Data extends Chunk {
    private byte[] data;

    /**
     * Getter for the data in this chunk
     * @return the data in this chunk
     */
    public byte[] getData() {
        return this.data;
    }
    /**
     * Constructor for a data chunk
     * @param id The ID of this chunk
     * @param size The size of this chunk
     * @param data The data stored in this chunk
     */
    public Data(byte[] data, Integer index) {
        super(new String(Arrays.copyOfRange(data, index, index + 4)), hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        index += 8;
        this.data = Arrays.copyOfRange(data, index, index + this.getSize());
        index += data.length;
    }

     @Override
    public String toString() {
        return "Data [id=" + this.getId() + ", size=" + this.getSize() + "]";
    }    
}
