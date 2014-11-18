package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.requestmethod.HttpPutMethodHandler;
import com.undecided.mocks.MockDirectoryLister;
import com.undecided.mocks.MockFile;
import com.undecided.utils.DirectoryLister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        RequestHeader requestHeader = new RequestHeader("PUT /form HTTP/1.1");
        requestHeader.parse();

        String fileName = Server.startDirectory + requestHeader.getRequestUrl();

        HttpPutMethodHandler handler = new HttpPutMethodHandler(requestHeader);
        MockDirectoryLister lister = new MockDirectoryLister(new MockFile(fileName));
        lister.exists = true;
        handler.setDirectoryLister( lister );

        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
    }

}
