/**
 * This class can parse and store a LIST chunk.
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.Arrays;

public class List extends Chunk {
    private String typeID;
    private byte[] data;

    /**
     * Constructor for a new LIST chunk
     * @param data The data where the chunk is stored
     * @param index The index in the data where the first byte of the chunk header is located
     */
    public List(byte[] data, Integer index) {
        super(new String(Arrays.copyOfRange(data, index, index + 4)), hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        index += 8;
        this.typeID = new String(Arrays.copyOfRange(data, index, index + 4));
        index += 4;
        this.data = Arrays.copyOfRange(data, index, index + this.getSize() - 4);
        index += data.length;
    }
    /**
     * Getter for the type ID field, which represents the type of information stored in the list
     * @return the type of this list
     */
    public String getTypeID() {
        return typeID;
    }
    /**
     * Getter for the data stored in this list
     * @return the data stored in this list
     */
    public byte[] getData() {
        return data;
    }
    @Override
    public String toString() {
        return "List [id=" + this.getId() + ", size=" + this.getSize() + ", typeID=" + typeID + ", data=" + new String(data) + "]";
    }

    
    
}
