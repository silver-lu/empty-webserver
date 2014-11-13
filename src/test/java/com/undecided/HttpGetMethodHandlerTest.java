package com.undecided;

import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.HttpGetMethodHandler;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/13/14.
 */
public class HttpGetMethodHandlerTest {

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }

    @Test
    public void testPathThatAreDirectoryIsHandledWithReturningDirectoryListing() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /src HTTP/1.1");
        requestHeader.parse();
        HttpGetMethodHandler handler = new HttpGetMethodHandler(requestHeader);
        handler.processRequest();
        assertEquals("HTTP/1.1 200 OK", handler.getResponse().split(System.lineSeparator())[0]);
        assertTrue(handler.getResponse().contains("main"));
    }

    @Test
    public void testPathThatAreFileIsHandledWithReturningFileContent() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /pom.xml HTTP/1.1");
        requestHeader.parse();
        HttpGetMethodHandler handler = new HttpGetMethodHandler(requestHeader);
        handler.processRequest();
        System.out.println(handler.getResponse());
        String[] lines = handler.getResponse().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
        assertEquals("</project>", lines[lines.length - 1]);
    }
}
