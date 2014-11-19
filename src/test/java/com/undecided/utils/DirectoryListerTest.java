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
        byte[] fileContent = directoryLister.getFileContent();
        String[] lines = new String(fileContent).split(System.lineSeparator());
        assertEquals("</project>", lines[lines.length - 1]);
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForHtmlFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("htmlFile.html"));
        assertEquals("text/html", directoryLister.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForXmlFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("xmlFile.xml"));
        assertEquals("text/xml", directoryLister.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForTxtFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("textFile.txt"));
        assertEquals("text/plain", directoryLister.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForJpegFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("jpegFile.jpeg"));
        assertEquals("image/jpeg", directoryLister.getFileMimeType());

        directoryLister = new DirectoryLister(new MockFile("jpegFile.jpg"));
        assertEquals("image/jpeg", directoryLister.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForGifFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("gifFile.gif"));
        assertEquals("image/gif", directoryLister.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForPngFileExtension() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new MockFile("pngFile.png"));
        assertEquals("image/png", directoryLister.getFileMimeType());
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

    @Test
    public void testWeCanObtainCorrectSha1HashCheckSumOfFile() throws Exception {
        DirectoryLister directoryLister = new DirectoryLister(new File("pom.xml"));
        assertEquals("27c03f744e06fdd68399df130d0d3ef3a0de2138", directoryLister.getCheckSum());
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
