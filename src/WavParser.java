/**
 * Parses a wave (.wav) file to a tree structure of chunks.
 * This is a good website if you are trying to understand the Wave file format, I had great help of it when writing this program:
 * https://ccrma.stanford.edu/courses/422-winter-2014/projects/WaveFormat/
 * Author: Axel Karlsson, axelk@mailbox.org
 */

import chunk.*;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Arrays;

public class WavParser {

    /**
     * Creates a tree of Chunk objectes from a .wav file.
     * @param filePath The path to the .wav file
     * @throws FileNotFoundException Is thrown if the file could not be found
     */
    public static Chunk parseFile(String filePath) throws FileNotFoundException, IOException {
        byte[] data = Files.readAllBytes(Paths.get(filePath));

        // In a wav file the top chunk should always be a riff chunk
        byte[] buff = Arrays.copyOfRange(data, 0, 4);
        if (Arrays.equals(buff, new byte[] {'R', 'I', 'F', 'F'})) {
            return new Riff(data, 0);
        } else {
            throw new IOException("Unexpected block");
        }     
    }
}