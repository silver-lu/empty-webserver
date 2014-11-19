package com.undecided.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileSystemWrapper {
    private DirectoryLister directoryLister;
    private File baseDirectory;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private FileInspector fileInspector;

    public FileSystemWrapper(File baseDirectory) {
        this.baseDirectory = baseDirectory;
        this.fileReader = new FileReader(baseDirectory);
        this.fileWriter = new FileWriter(baseDirectory);
        this.fileInspector = new FileInspector(baseDirectory);
        this.directoryLister = new DirectoryLister(baseDirectory);
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public FileInspector getFileInspector() {
        return fileInspector;
    }

    public DirectoryLister getDirectoryLister() {
        return directoryLister;
    }
}
