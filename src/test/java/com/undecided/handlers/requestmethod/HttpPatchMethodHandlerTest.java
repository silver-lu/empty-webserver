package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.requests.Request;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/18/14.
 */
public class HttpPatchMethodHandlerTest {

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }

    @Test
    public void testThatPatchForFileThatExistsReturnA204Response() throws Exception {
        String rawRequest = "PATCH /pom.xml HTTP/1.1" + System.lineSeparator();
        rawRequest += "Host: www.example.com" + System.lineSeparator();
        rawRequest += "Content-Type: application/example" + System.lineSeparator();
        rawRequest += "If-Match: \"e0023aa4e\"" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();

        HttpPatchMethodHandler handler = new HttpPatchMethodHandler(request);
        handler.processRequest();
        assertEquals("HTTP/1.1 204 No Content", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
        assertTrue(handler.getResponse().getHeader().contains("ETag: \"e0023aa4e\""));
    }
}
