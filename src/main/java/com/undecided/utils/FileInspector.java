package com.undecided.utils;

import com.undecided.constants.MimeTypeConstant;
import com.undecided.enums.FileExtension;

import java.io.File;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileInspector {
    private File file;

    public FileInspector(File file) {
        this.file = file;
    }

    public boolean isFile() {
        return file.isFile();
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public boolean exists() {
        return file.exists();
    }

    private FileExtension getFileExtension() {
        String filename = file.getName();
        String extensionString = filename.substring(filename.lastIndexOf('.') + 1);
        return MimeTypeConstant.EXTENTION.get(extensionString);
    }

    public String getFileMimeType() {
        return MimeTypeConstant.MIME_TYPE.get(getFileExtension());
    }
}
