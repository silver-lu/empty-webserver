package com.undecided;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.*;

/**
 * Created by silver.lu on 11/10/14.
 */
public class DirectoryListerTest {


    @Test
    public void testCanPullUpReadableFilesInCurrentDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("."));
        List<File> files = directoryLister.getReadableFiles();

        assertTrue(pathExists(files, "pom.xml"));
    }

    @Test
    public void testCanPullUpDirectoriesInGivenDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("."));
        List<File> files = directoryLister.getReadableDirectories();

        assertTrue(pathExists(files, "src"));
    }

    @Test
    public void testHiddenFilesAreNotPulledUp() throws Exception {
        MockFile mockFile = new MockFile(".");

        List<File> fakeFiles = new ArrayList<File>();
        MockFile normalFile = new MockFile("./abc");
        normalFile.setIsFile(true);
        fakeFiles.add(normalFile);


        MockFile hiddenFile = new MockFile("HiddenFile");
        hiddenFile.setIsFile(true);
        hiddenFile.flagAsHidden();
        fakeFiles.add(hiddenFile);

        mockFile.setFiles(fakeFiles);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        List<File> files = directoryLister.getReadableFiles();
        System.out.println(files.toString());
        assertTrue(pathExists(directoryLister.getReadableFiles(), "./abc"));
        assertFalse(pathExists(directoryLister.getReadableFiles(), "./HiddenFile"));

    }

    @Test
    public void testHiddenDirectoriesAreNotPulledUp() throws Exception {

        MockFile mockFile = new MockFile(".");
        List<File> fakeDirs = new ArrayList<File>();

        MockFile dir1 = new MockFile("DIR1");
        dir1.setIsDirectory(true);

        fakeDirs.add(dir1);


        MockFile hiddenDir = new MockFile("HIDDENDIR");
        hiddenDir.setIsDirectory(true);
        hiddenDir.flagAsHidden();
        fakeDirs.add(hiddenDir);


        mockFile.setFiles(fakeDirs);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        assertTrue(pathExists(directoryLister.getReadableDirectories(), "DIR1"));
        assertFalse(pathExists(directoryLister.getReadableDirectories(), "HIDDENDIR"));

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
