package com.undecided.utils;

import com.undecided.mocks.MockFile;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        directoryLister.parseDirectory();
        List<File> files = directoryLister.getReadableFiles();
        assertTrue(pathExists(files, "pom.xml"));
    }

    @Test
    public void testCanPullUpDirectoriesInGivenDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("."));
        directoryLister.parseDirectory();
        List<File> files = directoryLister.getReadableDirectories();

        assertTrue(pathExists(files, "src"));
    }


    @Test
    public void testHiddenFilesAreNotPulledUp() throws Exception {
        MockFile mockFile = new MockFile(".");

        List<File> fakeFiles = new ArrayList<File>();
        fakeFiles.add(new MockFile("abc", MockFile.FILE));
        fakeFiles.add(new MockFile("hiddenFile", MockFile.FILE, MockFile.HIDDEN));

        mockFile.setFiles(fakeFiles);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        directoryLister.parseDirectory();
        List<File> files = directoryLister.getReadableFiles();

        assertTrue(pathExists(files, "abc"));
        assertFalse(pathExists(files, "hiddenFile"));
    }

    @Test
    public void testHiddenDirectoriesAreNotPulledUp() throws Exception {

        MockFile mockFile = new MockFile(".");
        List<File> fakeFiles = new ArrayList<File>();

        fakeFiles.add(new MockFile("abc", MockFile.DIRECTORY ));
        fakeFiles.add(new MockFile("hiddenDirectory", MockFile.DIRECTORY, MockFile.HIDDEN));

        mockFile.setFiles(fakeFiles);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        directoryLister.parseDirectory();
        List<File> files = directoryLister.getReadableDirectories();

        assertTrue(pathExists(files, "abc"));
        assertFalse(pathExists(files, "hiddenDirectory"));
    }

    @Test
    public void testGeneratingHrefLinks() throws Exception {
        MockFile mockFile = new MockFile("./");
        List<File> fakeFiles = new ArrayList<File>();

        fakeFiles.add(new MockFile("abc", MockFile.FILE));
        fakeFiles.add(new MockFile("def", MockFile.FILE));
        fakeFiles.add(new MockFile("ghi", MockFile.DIRECTORY));

        mockFile.setFiles(fakeFiles);

        DirectoryLister directoryLister = new DirectoryLister(mockFile);
        //DirectoryLister directoryLister = new DirectoryLister(new GetFile("/Users/yvonne.wang/JavaTraining/cob_spec/public"));
        directoryLister.parseDirectory();
        String htmlLinks = directoryLister.getLinkableDirectory();
        assertTrue(htmlLinks.contains("<a href='/abc'"));
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
