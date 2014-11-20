package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.mocks.MockFile;
import com.undecided.mocks.MockFileReader;
import com.undecided.mocks.MockFileSystemWrapper;
import com.undecided.mocks.MockFileWriter;
import com.undecided.requests.Request;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/18/14.
 */
public class HttpPatchMethodHandlerTest {
    private MockFileSystemWrapper fsWrapper;
    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;

        MockFile file = new MockFile("virtual-path.txt");
        file.setExists(true);
        fsWrapper = new MockFileSystemWrapper(file);
        MockFileWriter fileWriter = new MockFileWriter(file);
        fsWrapper.setFileWriter(fileWriter);
        MockFileReader fileReader = new MockFileReader(file);
        fsWrapper.setFileReader(fileReader);
    }

    @Test
    public void testThatPatchForFileThatExistsAndMatchesTheHashSumReturnA204Response() throws Exception {
        String rawRequest = "PATCH /virtual-path.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "If-Match: 6367c48dd193d56ea7b0baad25b19455e529f5ee" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();
        MockFileSystemWrapper.Content = "abc123".getBytes();

        HttpPatchMethodHandler handler = new HttpPatchMethodHandler(request);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();
        assertEquals("HTTP/1.1 204 No Content", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
        assertTrue(handler.getResponse().getHeader().contains("ETag: ecef6bf38a90d04f9cd2d7535a0b8b9a5148b68e"));
    }

    @Test
    public void testThatPatchForFileThatExistsAndMatchesTheHashSumPatchIsApplied() throws Exception {
        String rawRequest = "PATCH /virtual-path.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "If-Match: 6367c48dd193d56ea7b0baad25b19455e529f5ee" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();
        MockFileSystemWrapper.Content = "abc123".getBytes();

        HttpPatchMethodHandler handler = new HttpPatchMethodHandler(request);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();
        assertEquals("abcdefghijabcdefghij", new String(MockFileSystemWrapper.Content));
    }

    @Test
    public void testThatPatchForFileThatExistsButTheHashSumDoNotMatchReturnA412Response() throws Exception {
        String rawRequest = "PATCH /virtual-path.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "If-Match: e0023aa4e" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();
        MockFileSystemWrapper.Content = "abc123".getBytes();

        HttpPatchMethodHandler handler = new HttpPatchMethodHandler(request);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();
        assertEquals("HTTP/1.1 412 Precondition Failed", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
        assertTrue(handler.getResponse().getHeader().contains("ETag: 6367c48dd193d56ea7b0baad25b19455e529f5ee"));
    }

    @Test
    public void testThatPatchForFileThatDoNotExistReturnA404Response() throws Exception {
        String rawRequest = "PATCH /file-do-not-exist.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "If-Match: 6367c48dd193d56ea7b0baad25b19455e529f5ee" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();

        HttpPatchMethodHandler handler = new HttpPatchMethodHandler(request);
        handler.processRequest();
        assertEquals("HTTP/1.1 404 Not Found", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
    }
}
