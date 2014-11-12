package com.undecided;

import java.io.File;
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

    public DirectoryLister(File baseDirectory) {
        String rootDir = "./";

        this.readableFiles = new ArrayList<File>();
        this.readableDirectories = new ArrayList<File>();
        this.readableFilesAndDirectories = new ArrayList<File>();
        this.allFiles = new ArrayList<File>();
        this.allDirectories = new ArrayList<File>();

        this.baseDirectory = baseDirectory;
        parseDirectory();
    }

    private void parseDirectory() {
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

    public List<File> getReadableFilesAndDirectories() {
        return readableFilesAndDirectories;
    }

    public List<File> getReadableFiles() {
        return readableFiles;
    }

    public List<File> getReadableDirectories() {
        return readableDirectories;
    }
}
