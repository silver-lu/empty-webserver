package com.undecided.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileWriter {
    protected File file;
    protected byte[] content;

    public FileWriter(File file) {
        this.file = file;
    }
    
    public void setContent(byte[] content) {
        this.content = content;
    }

    public void write() throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content);
        Files.write(Paths.get(file.getAbsolutePath()), "\n".getBytes(), StandardOpenOption.APPEND);
    }

    public String getCheckSum() throws NoSuchAlgorithmException {
        MessageDigest crypto = MessageDigest.getInstance("SHA-1");
        crypto.reset();
        crypto.update(content);
        return bytesToHex(crypto.digest());
    }

    private static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    public void delete() {
        file.delete();
    }
}
