package com.undecided;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

 /*   @Test
    public void testServerRootStartsInCurrentDirectory() {
        DirectoryLister directoryLister = new DirectoryLister(new File("/"));
        String response = directoryLister.getStringReadableFilesAndDirectories();
        System.out.println(response);
        assertTrue(response.contains("src"));
    }*/

    @Test
    public void testHiddenFilesAreNotPulledUp() throws Exception {
        MockFile mockFile = new MockFile(".");

        List<File> fakeFiles = new ArrayList<File>();
        fakeFiles.add(new MockFile("abc"));


        MockFile hiddenFile = new MockFile("HiddenFile");
        hiddenFile.flagAsHidden();
        fakeFiles.add(hiddenFile);

        mockFile.setFiles(fakeFiles);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        List<File> files = directoryLister.getReadableFiles();
        System.out.println(files.toString());
        assertTrue(pathExists(files, "abc"));
        assertFalse(pathExists(files, "HiddenFile"));
    }

    @Test
    public void testHiddenDirectoriesAreNotPulledUp() throws Exception {

    }

    private boolean pathExists(List<File> paths, String target) {
        for  ( File path : paths ) {
            if ( path.getPath().contains(target)) {
                return true;
            }
        }
        return false;
     }
}
