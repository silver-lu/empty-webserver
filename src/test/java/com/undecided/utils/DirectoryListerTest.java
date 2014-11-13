package com.undecided.utils;

import com.undecided.mocks.MockFile;
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
    public void testWeAreGettingDirectoryBackAsDirectory() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("src"));
        assertTrue(directoryLister.isDirectory());

        directoryLister = new DirectoryLister(new File("pom.xml"));
        assertFalse(directoryLister.isDirectory());
    }

    @Test
    public void testWeAreGettingFileBackAsFile() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("pom.xml"));
        assertTrue(directoryLister.isFile());

        directoryLister = new DirectoryLister(new File("src"));
        assertFalse(directoryLister.isFile());
    }

    @Test
    public void testCanCheckIfAFilePathExistsOrNot() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("pom.xml"));
        assertTrue(directoryLister.exists());

        directoryLister = new DirectoryLister(new File("abc.123"));
        assertFalse(directoryLister.exists());
    }

    @Test
    public void testReadContentOfAFile() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("pom.xml"));
        String fileContent = directoryLister.getFileContent();
        String[] lines = fileContent.split(System.lineSeparator());
        assertEquals("</project>", lines[lines.length - 1]);
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
        fakeFiles.add(new MockFile("abc", MockFile.FILE ));
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
        List<File> fakeDirs = new ArrayList<File>();

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

    private boolean pathExists(List<File> paths, String target) {
        for  ( File path : paths ) {
            if ( path.getPath().contains(target)) {
                return true;
            }
        }
        return false;
     }
}
