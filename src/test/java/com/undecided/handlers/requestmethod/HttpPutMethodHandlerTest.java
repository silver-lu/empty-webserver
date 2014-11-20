package com.undecided.handlers.requestmethod;

import com.undecided.mocks.*;
import com.undecided.requests.Request;
import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.requestmethod.HttpPutMethodHandler;
import com.undecided.utils.FileSystemWrapper;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;

import static org.junit.Assert.assertEquals;

/**
 * Created by anthony.chai on 11/15/14.
 */
public class HttpPutMethodHandlerTest {
    private MockFileSystemWrapper fsWrapper;
    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;

        MockFile file = new MockFile("virtual-path.txt");
        fsWrapper = new MockFileSystemWrapper(file);
        MockFileWriter fileWriter = new MockFileWriter(file);
        fsWrapper.setFileWriter(fileWriter);
        MockFileReader fileReader = new MockFileReader(file);
        fsWrapper.setFileReader(fileReader);
    }

    @Test
    public void testPutReturns200ForExistingFile() throws Exception {
        Request request = new Request("PUT /form HTTP/1.1");
        request.parse();

        String fileName = Server.startDirectory + request.getRequestHeader().getRequestUrl();
        HttpPutMethodHandler handler = new HttpPutMethodHandler(request);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
    }

}
