package com.undecided.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileReader {
    private File file;
    private byte[] content;

    public FileReader(File file) {
        this.file = file;
    }

    public void read() {
        try {
            content = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            content =  new byte[0];
        }
    }

    public byte[] getContent() {
        return content;
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
}
