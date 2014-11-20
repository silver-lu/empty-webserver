package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.mocks.MockFile;
import com.undecided.mocks.MockFileReader;
import com.undecided.mocks.MockFileSystemWrapper;
import com.undecided.mocks.MockFileWriter;
import com.undecided.requests.Request;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/19/14.
 */
public class HttpPostMethodHandlerTest {
    private MockFileSystemWrapper fsWrapper;
    private MockFile file;

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;

        file = new MockFile("virtual-path.txt");
        fsWrapper = new MockFileSystemWrapper(file);
        MockFileWriter fileWriter = new MockFileWriter(file);
        fsWrapper.setFileWriter(fileWriter);
        MockFileReader fileReader = new MockFileReader(file);
        fsWrapper.setFileReader(fileReader);
    }

    @Test
    public void testPostReturns200WhenFileDoesNotExist() throws Exception {
        Request request = new Request("POST /form HTTP/1.1");
        request.parse();

        HttpPutMethodHandler handler = new HttpPutMethodHandler(request);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
    }

    @Test
    public void testThatPostForFileThatDoesNotExistWritesTheBody() throws Exception {
        String rawRequest = "POST /virtual-path.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();

        HttpPostMethodHandler handler = new HttpPostMethodHandler(request);
        file.setExists(false);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();
        assertEquals("abcdefghijabcdefghij", new String(MockFileSystemWrapper.Content));
    }

    @Test
    public void testThatPostForFileThatExist() throws Exception {
        String rawRequest = "POST /virtual-path.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();

        HttpPostMethodHandler handler = new HttpPostMethodHandler(request);
        file.setExists(true);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();

    }
}
