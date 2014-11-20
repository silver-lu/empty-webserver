package com.undecided.mocks;

import com.undecided.utils.FileReader;

/**
 * Created by silver.lu on 11/19/14.
 */
public class MockFileReader extends FileReader {
    public MockFileReader(MockFile file) {
        super(file);
    }

    @Override
    public void read() {
        content = MockFileSystemWrapper.Content;
    }
}
