/**
 * This class can parse and store a chunk containing an ID3 tag.
 * The spec for the ID3 format can be found here: https://mutagen-specs.readthedocs.io/en/latest/id3/id3v2.2.html
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.Arrays;

public class ID3 extends Chunk {

    private String fileID;
    private String version;
    private Integer I3Size;
    private Boolean unsynchronization;
    private Boolean compression;
    private Boolean experimental;
    private Boolean footer;

    /**
     * Constructor for a chunk containing an ID3 tag.
     * @param data The data where the chunk is stored
     * @param index The index in the data where the first byte of the chunk header is located
     */
    public ID3(byte[] data, Integer index) {
        super(new String(Arrays.copyOfRange(data, index, index + 4)), hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        // Parse all ID3 fields
        this.fileID = new String(Arrays.copyOfRange(data, index, index + 3));
        index += 3;
        this.version = String.format("%d.%d", hex2dec(Arrays.copyOfRange(data, index, index + 1)), hex2dec(Arrays.copyOfRange(data, index + 1, index + 2)));
        index += 2;
        byte[] flags = Arrays.copyOfRange(data, index, index + 1);
        index += 1;
        this.unsynchronization = (flags[0] & 0x80) == 0x80? true : false;
        this.compression = (flags[0] & 0x40) == 0x40? true : false;
        this.experimental = (flags[0] & 0x20) == 0x20? true : false;
        this.footer = (flags[0] & 0x10) == 0x10? true : false;

        this.I3Size = hex2dec(Arrays.copyOfRange(data, index, index + 4));
        index += 4;
    }
    /**
     * Getter for the file ID as specified in the ID3 specification
     * @return the ID3 file id
     */
    public String getFileID() {
        return fileID;
    }
    /**
     * Getter for the ID3 version 
     * @return The ID3 version of this chunk
     */
    public String getVersion() {
        return version;
    }
    /**
     * Getter for the size of the ID3 tag. Excludes the header which i 10 bytes
     * @return the size of this I3D tag
     */
    public Integer getI3Size() {
        return I3Size;
    }
    /**
     * Getter for the state of the unsynchronization flag
     * @return the value of the unsynchronization flag
     */
    public Boolean getUnsynchronization() {
        return unsynchronization;
    }
    /**
     * Getter for the value of the compression flag
     * @return The value of the compression tag
     */
    public Boolean getCompression() {
        return compression;
    }
    /**
     * Getter for the value of the experimental flag
     * @return the value of the experimental flag
     */
    public Boolean getExperimental() {
        return experimental;
    }
    /**
     * Getter for the value of the footer flag
     * @return The value of the footer flag
     */
    public Boolean getFooter() {
        return footer;
    }

    @Override
    public String toString() {
        return "ID3 [fileID=" + fileID + ", version=" + version + ", I3Size=" + I3Size + ", unsynchronization="
                + unsynchronization + ", compression=" + compression + ", experimental=" + experimental + ", footer="
                + footer + "]";
    }
}
