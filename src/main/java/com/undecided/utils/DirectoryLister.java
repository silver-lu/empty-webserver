package com.undecided.utils;

import com.undecided.constants.MimeTypeConstant;
import com.undecided.enums.FileExtension;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.spi.FileTypeDetector;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by silver.lu on 11/11/14.
 */
public class DirectoryLister {
    private File baseDirectory;
    private List<File> readableFiles;
    private List<File> readableDirectories;
    private List<File> readableFilesAndDirectories;
    private List<File> allFiles;
    private List<File> allDirectories;

    private FileReader fileReader;
    private FileWriter fileWriter;
    private FileInspector fileInspector;

    public DirectoryLister(File baseDirectory) {

        this.readableFiles = new ArrayList<File>();
        this.readableDirectories = new ArrayList<File>();
        this.readableFilesAndDirectories = new ArrayList<File>();
        this.allFiles = new ArrayList<File>();
        this.allDirectories = new ArrayList<File>();
        this.baseDirectory = baseDirectory;
        this.fileReader = new FileReader(baseDirectory);
        this.fileWriter = new FileWriter(baseDirectory);
    }

    public void parseDirectory() {
        File[] files = baseDirectory.listFiles();
        for (File file : files) {
            if ( file.isFile() ) {
                if ( !file.isHidden() ) {
                    this.readableFiles.add(file);
                    this.readableFilesAndDirectories.add(file);
                }
                this.allFiles.add(file);
            }
            if ( file.isDirectory() ) {
                if ( !file.isHidden() ) {
                    this.readableDirectories.add(file);
                    this.readableFilesAndDirectories.add(file);
                }
                this.allDirectories.add(file);
            }
        }
    }

    public String getStringReadableFilesAndDirectories() {
        String str = "";
        for (File file : getReadableFilesAndDirectories()) {
            str += file.getName() + " ";
        }
        return str;
    }

    public List<String> getListReadableFilesAndDirectories() {
        List<String> str = new ArrayList<String>();
        for (File file : getReadableFilesAndDirectories()) {
            str.add(file.getName());
        }
        return str;
    }

    public String getLinkableDirectory() {
        HtmlGenerator htmlGenerator = new HtmlGenerator(getListReadableFilesAndDirectories());
        return htmlGenerator.getBody();
    }

    public List<File> getReadableFilesAndDirectories() {
        return readableFilesAndDirectories;
    }

    public List<File> getReadableFiles() {
        return readableFiles;
    }

    public List<File> getReadableDirectories() {
        return readableDirectories;
    }

    public FileInspector fileInspector() {
        return fileInspector;
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }
}
