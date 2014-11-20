package com.undecided.utils;

import org.junit.*;

import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileReaderWriterTest {

    private File file;

    @Before
    public void setUp() throws Exception {
        file = new File("./test-write.txt");
        if ( file.exists() ) {
            file.delete();
        }
    }

    @Test
    public void testWeCanWriteToAFileThenReadFromIt() throws Exception {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.setContent("abc123".getBytes());
        fileWriter.write();

        FileReader fileReader = new FileReader(file);
        fileReader.read();
        assertEquals("abc123\n", new String(fileReader.getContent()));
    }

    @Test
    public void testWeCanWriteMultipleTimesToAFileThenReadFromItToObtainLatestWrite() throws Exception {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.setContent("abc123".getBytes());
        fileWriter.write();

        fileWriter.setContent("123abc".getBytes());
        fileWriter.write();

        FileReader fileReader = new FileReader(file);
        fileReader.read();
        assertEquals("123abc\n", new String(fileReader.getContent()));
    }

    @Test
    public void testWeCanGetTheSHA1HashSumOfAFile() throws Exception {
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.setContent("abc123".getBytes());
        fileWriter.write();

        FileReader fileReader = new FileReader(file);
        fileReader.read();
        assertEquals("61ee8b5601a84d5154387578466c8998848ba089", fileReader.getCheckSum());
    }

    @After
    public void tearDown() throws Exception {
        file = new File("./test-write.txt");
        if ( file.exists() ) {
            file.delete();
        }
    }
}
