package com.undecided.mocks;

import com.undecided.utils.*;

import java.io.File;

/**
 * Created by silver.lu on 11/19/14.
 */
public class MockFileSystemWrapper extends FileSystemWrapper{
    public MockFileSystemWrapper(File baseDirectory) {
        super(baseDirectory);
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setDirectoryLister(DirectoryLister directoryLister) {
        this.directoryLister = directoryLister;
    }

    public void setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
    }

    public void setFileInspector(FileInspector fileInspector) {
        this.fileInspector = fileInspector;
    }
}
