package com.undecided.handlers.requestmethod;

import com.undecided.mocks.MockFileSystemWrapper;
import com.undecided.requests.Request;
import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.requestmethod.HttpPutMethodHandler;
import com.undecided.mocks.MockDirectoryLister;
import com.undecided.mocks.MockFile;
import com.undecided.utils.FileSystemWrapper;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.FileSystem;

import static org.junit.Assert.assertEquals;

/**
 * Created by anthony.chai on 11/15/14.
 */
public class HttpPutMethodHandlerTest {
    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }



    @Test
    public void testPutReturns200ForExistingFile() throws Exception {
        Request request = new Request("PUT /form HTTP/1.1");
        request.parse();

        String fileName = Server.startDirectory + request.getRequestHeader().getRequestUrl();
        HttpPutMethodHandler handler = new HttpPutMethodHandler(request);
        MockFileSystemWrapper fsWrapper = new MockFileSystemWrapper(new MockFile(fileName));
        MockDirectoryLister lister = new MockDirectoryLister(new MockFile(fileName));
        lister.exists = true;
        fsWrapper.setDirectoryLister(lister);
        handler.setFileSystemWrapper(fsWrapper);

        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
    }

}
