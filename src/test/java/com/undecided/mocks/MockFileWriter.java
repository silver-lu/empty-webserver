package com.undecided.mocks;

import com.undecided.utils.FileReader;
import com.undecided.utils.FileWriter;

import java.io.File;

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
}
