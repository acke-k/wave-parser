/**
 * Class for representing a FMT chunk. This chunk contains metainformation regarding the parent chunk/the entire file
 * Author: Axel Karlsson, axelk@mailbox.org
 */

package chunk;

import java.util.Arrays;

public class Fmt extends Chunk {
    private Integer audioFormat;
    private Integer numChannels;
    private Integer sampleRate;
    private Integer byteRate;
    private Integer blockAlign;
    private Integer bitsPerSample;

    /**
     * Getter for the audioformat field
     * @return the audioformat this file
     */
    public Integer getAudioFormat() {
        return audioFormat;
    }
    /**
     * Getter for the number of channels in this file
     * @return the number of channels in this audio file
     */
    public Integer getNumChannels() {
        return numChannels;
    }
    /**
     * Getter for the sample rate of this file
     * @return the sample rate of this file
     */
    public Integer getSampleRate() {
        return sampleRate;
    }
    /**
     * Getter for the byte rate, i.e. the number of bytes/seconds generated when playing this audio file 
     * @return the byte rate for this file
     */
    public Integer getByteRate() {
        return byteRate;
    }
    /**
     * Getter for the block align, meaning the size of each sample/data point in this audio file
     * @return the block align for this audio file
     */
    public Integer getBlockAlign() {
        return blockAlign;
    }
    /**
     * Getter for the number of bits per sample/data point in this audio file
     * @return The number bits per sample
     */
    public Integer getBitsPerSample() {
        return bitsPerSample;
    }

    /**
     * Constructor for the Fmt class. Returns an object representing a FMT chunk
     * @param data The data where the chunk is stored
     * @param index The index in the data where the first byte of the chunk header is located
     */
    public Fmt(byte[] data, Integer index) {
        super(new String(Arrays.copyOfRange(data, index, index + 4)), hex2dec(changeEndian(Arrays.copyOfRange(data, index + 4, index + 8))));
        index += 8;
        this.audioFormat = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 2)));
        index += 2;
        this.numChannels = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 2)));
        index += 2;
        this.sampleRate = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 4)));
        index += 4;
        this.byteRate = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 4)));
        index += 4;
        this.blockAlign = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 2)));
        index += 2;
        this.bitsPerSample = hex2dec(changeEndian(Arrays.copyOfRange(data, index, index + 2)));
        index += 2;
    }

    @Override
    public String toString() {
        return "Fmt [id=" + this.getId() + ", size=" + this.getSize() + ", audioFormat=" + audioFormat + ", numChannels=" + numChannels + ", sampleRate=" + sampleRate
                + ", byteRate=" + byteRate + ", blockAlign=" + blockAlign + ", bitsPerSample=" + bitsPerSample + "]";
    }
}
