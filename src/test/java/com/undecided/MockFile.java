package com.undecided;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silver.lu on 11/11/14.
 */
public class MockFile extends File {

    List<File> files = new ArrayList<File>();
    private boolean hidden;
    private boolean isDirectory;
    private boolean isFile;

    public MockFile(String pathname) {
        super(pathname);

    }

    @Override
    public File[] listFiles() {
        return files.toArray(new File[files.size()]);
    }

    public void setIsFile(boolean isFile){
        this.isFile = isFile;
    }

    @Override
    public boolean isFile() {
        return isFile;
    }

    public void setIsDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    @Override
    public boolean isDirectory(){
        return isDirectory;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }


    public void flagAsHidden() {
        hidden = true;
    }
}
