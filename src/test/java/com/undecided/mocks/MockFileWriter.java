package com.undecided.mocks;

import com.undecided.utils.FileReader;
import com.undecided.utils.FileWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by silver.lu on 11/19/14.
 */
public class MockFileWriter extends FileWriter {


    public MockFileWriter(File file) {
        super(file);
    }

    @Override
    public void write() {
        MockFileSystemWrapper.Content = content;
    }

    @Override
    public void delete() { file.delete(); }

}
