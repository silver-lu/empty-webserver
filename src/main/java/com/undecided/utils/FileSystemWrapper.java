package com.undecided.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileSystemWrapper {
    protected DirectoryLister directoryLister;
    protected File baseDirectory;
    protected FileReader fileReader;
    protected FileWriter fileWriter;
    protected FileInspector fileInspector;

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
