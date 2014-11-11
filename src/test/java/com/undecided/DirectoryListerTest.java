package com.undecided;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by silver.lu on 11/10/14.
 */
public class DirectoryListerTest {


    @Test
    public void testCanPullUpReadableFilesInCurrentDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(".");
        List<File> files = directoryLister.getReadableFiles();

        assertTrue(pathExists(files, "./pom.xml"));

    }

    @Test
    public void testCanPullUpDirectoriesInGivenDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(".");
        List<File> files = directoryLister.getReadableDirectories();

        assertTrue(pathExists(files, "./src"));
    }

    private boolean pathExists(List<File> paths, String target) {
        for  ( File path : paths ) {
            if ( path.getPath().equals(target)) {
                return true;
            }
        }
        return false;
     }
}
