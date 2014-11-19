package com.undecided.utils;

import com.undecided.mocks.MockFile;
import org.junit.Test;

import java.io.File;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by silver.lu on 11/19/14.
 */
public class FileInspectorTest {

    @Test
    public void testWeAreGettingDirectoryBackAsDirectory() throws Exception {
        FileInspector inspector = new FileInspector(new File("src"));
        assertTrue(inspector.isDirectory());

        inspector = new FileInspector(new File("pom.xml"));
        assertFalse(inspector.isDirectory());
    }

    @Test
    public void testWeAreGettingFileBackAsFile() throws Exception {
        FileInspector inspector = new FileInspector(new File("pom.xml"));
        assertTrue(inspector.isFile());

        inspector = new FileInspector(new File("src"));
        assertFalse(inspector.isFile());
    }

    @Test
    public void testCanCheckIfAFilePathExistsOrNot() throws Exception {
        FileInspector inspector = new FileInspector(new File("pom.xml"));
        assertTrue(inspector.exists());

        inspector = new FileInspector(new File("abc.123"));
        assertFalse(inspector.exists());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForHtmlFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("htmlFile.html"));
        assertEquals("text/html", inspector.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForXmlFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("xmlFile.xml"));
        assertEquals("text/xml", inspector.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForTxtFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("textFile.txt"));
        assertEquals("text/plain", inspector.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForJpegFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("jpegFile.jpeg"));
        assertEquals("image/jpeg", inspector.getFileMimeType());

        inspector = new FileInspector(new MockFile("jpegFile.jpg"));
        assertEquals("image/jpeg", inspector.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForGifFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("gifFile.gif"));
        assertEquals("image/gif", inspector.getFileMimeType());
    }

    @Test
    public void testWeAreAbleToRetrieveCorrectMimeTypeForPngFileExtension() throws Exception {
        FileInspector inspector = new FileInspector(new MockFile("pngFile.png"));
        assertEquals("image/png", inspector.getFileMimeType());
    }
}
