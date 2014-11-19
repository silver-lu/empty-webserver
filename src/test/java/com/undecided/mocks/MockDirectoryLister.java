package com.undecided.mocks;

import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by anthony.chai on 11/17/14.
 */
public class MockDirectoryLister extends DirectoryLister {

    public boolean exists;

    public MockDirectoryLister(File baseDirectory) {
        super(baseDirectory);
    }
}
