package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
<<<<<<< HEAD
=======

    @Test
    public void testInvalidRequestMethodWillReturn405() throws Exception {
        RequestHandler handler = new RequestHandler("FOO /test HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();
        assertEquals("HTTP/1.1 405 Method Not Allowed", response);
    }
>>>>>>> 15e979333a5c2607bfcdaec68f770ac4c21e5a13
}
