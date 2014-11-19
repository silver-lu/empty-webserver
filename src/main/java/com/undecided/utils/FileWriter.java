package com.undecided.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileWriter {
    private File file;
    private byte[] content;

    public FileWriter(File file) {
        this.file = file;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }

    public void write() throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content);
    }
}
