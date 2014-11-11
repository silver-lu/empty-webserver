package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by silver.lu on 11/11/14.
 */
public class RequestHandlerTest {

    @Test
    public void testInvalidRequestWillReturn400() throws Exception {

        RequestHandler handler = new RequestHandler("");
        handler.processRequest();
        String response = handler.getResponse();
        assertEquals("HTTP/1.1 400 Bad Request", response);
    }

    @Test
    public void testValidRequestWillReturn404ByDefault() throws Exception {
        RequestHandler handler = new RequestHandler("GET /test HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();
        assertEquals("HTTP/1.1 404 Not Found", response);
    }

    @Test
    public void testValidServerRootPathReturnsFilesAndFolders() throws Exception {
        RequestHandler handler = new RequestHandler("GET / HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();
        assertTrue(response.contains("src"));
    }

}
