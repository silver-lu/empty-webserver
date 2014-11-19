package com.undecided.utils;


import com.undecided.mocks.MockFile;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class SaveAsHtmlTest {
    @Test
    public void ShouldSaveFileAsHtml(){
        DirectoryLister directoryLister = new DirectoryLister(new File("."));

        //DirectoryLister directoryLister = new DirectoryLister(new GetFile("."));
        directoryLister.parseDirectory();
        List<String> files = directoryLister.getListReadableFilesAndDirectories();
        SaveAsHtml saveAsHtml = new SaveAsHtml(files.toString());
        saveAsHtml.saveFile();
        assertTrue(files.contains("index.html"));

        File file = new File("./index.html");
        file.delete();
    }
}
