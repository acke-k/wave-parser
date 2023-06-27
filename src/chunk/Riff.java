/**
 * Abstract class for representing a RIFF chunk. If you want to add another chunk type, look at the classes extending this class and you should get the jist of how it works.
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.Arrays;

public class Riff extends Chunk {
    private String format;

    /**
     * Getter for the format field.
     * @returns the format of this chunk
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * Parses a RIFF chunk from a data array.
     * @param data Data containing a RIFF chunk
     * @param index The index in data where the first byte of the RIFF header is
     */
    public Riff(byte data[], Integer index) {
        super("RIFF", hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        index += 8;
        // Read file format
        byte buff[];
        buff = Arrays.copyOfRange(data, index, index + 4);
        index += 4;
        this.format = new String(buff);

        // Find subchunks
        while (index < this.getSize()) {
            buff = Arrays.copyOfRange(data, index, index + 4);
            if (Arrays.equals(buff, new byte[] {'f', 'm', 't', ' '})) {
                Fmt fmtChunk = new Fmt(data, index);
                addSubChunk(fmtChunk);
                index += fmtChunk.getSize() + 8;
            }
            else if (Arrays.equals(buff, new byte[] {'d', 'a', 't', 'a'})) {
                Data dataChunk = new Data(data, index);
                addSubChunk(dataChunk);
                index += dataChunk.getSize() + 8;
            } 
            else if (Arrays.equals(buff, new byte[] {'L', 'I', 'S', 'T'})) {
                List listChunk = new List(data, index);
                addSubChunk(listChunk);
                index += listChunk.getSize() + 8;
            } 
            else if (Arrays.equals(buff, new byte[] {'i', 'd', '3', ' '})) {
                ID3 ID3Chunk = new ID3(data, index);
                addSubChunk(ID3Chunk);
                index += ID3Chunk.getSize() + 8;
            } 
            else {
                Unknown unknownChunk = new Unknown(data, index);
                addSubChunk(unknownChunk);
                index += unknownChunk.getSize();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Riff [id=" + this.getId() + ", size=" + this.getSize() + ", format=" + format + "]\n");
        sb.append("SubChunks:\n");
        for (Chunk c : this.getSubChunk())
            sb.append(String.format("\t%s\n", c.toString()));
        return sb.toString();
    }    
}
