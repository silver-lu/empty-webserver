package com.ericsmith;

import java.io.File;
import java.util.*;

/**
 * Created by silver.lu on 11/11/14.
 */
public class DirectoryLister {
    private String startDirectory;
    private List<File> files;
    private List<File> directories;
    private List<File> allReadableFilesAndDirectories;
    private List<File> allFiles;
    private List<File> allDirectories;

    public DirectoryLister(String startDirectory) {
        this.startDirectory = startDirectory;
        parseDirectory();
    }

    private void parseDirectory() {
        File dir = new File(startDirectory);
        for (File file : dir.listFiles()) {
            if ( file.isFile() ) {
                if ( !file.isHidden() ) {
                    files.add(file);
                    allReadableFilesAndDirectories.add(file);
                }
                allFiles.add(file);
            }
            if ( file.isDirectory() ) {
                if ( !file.isHidden() ) {
                    directories.add(file);
                    allReadableFilesAndDirectories.add(file);
                }
                allDirectories.add(file);
            }
        }
    }

    public List<File> getAllReadableFilesAndDirectories() {
        return allReadableFilesAndDirectories;
    }
}
