/**
 * An object of this class is created if an unknown chunk ID is encountered.
 * Author: Axel Karlsson, axelk@mailbox.org
 */
package chunk;

import java.util.Arrays;

public class Unknown extends Chunk {
    /**
     * Constructor for an unknown chunk.
     * @param data The data where the chunk is stored
     * @param index The index in the data where the first byte of the chunk header is located
     */
        public Unknown(byte[] data, Integer index) {
            super(new String(Arrays.copyOfRange(data, index, index + 4)), hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        }

        @Override
        public String toString() {
            return "Unknown block type [id=" + this.getId() + ", size=" + this.getSize() + "]";
        }
}
