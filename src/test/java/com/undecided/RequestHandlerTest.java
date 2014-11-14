package com.undecided;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
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
        assertEquals("HTTP/1.1 400 Bad Request", response.split("\r\n")[0]);
    }

    @Test
    public void testValidRequestWillReturn404ByDefault() throws Exception {
        RequestHandler handler = new RequestHandler("GET /test HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();
        assertEquals("HTTP/1.1 404 Not Found", response.split("\r\n")[0]);
    }


    @Test
    public void testValidRequestForRootDirectory() throws Exception {
        Server.startDirectory = "./";
        RequestHandler handler = new RequestHandler("GET / HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();

        assertEquals("HTTP/1.1 200 OK", response.split("\r\n")[0]);
    }

    @Test
    public void testInvalidRequestMethodWillReturn405() throws Exception {
        RequestHandler handler = new RequestHandler("FOO /test HTTP/1.1");
        handler.processRequest();
        String response = handler.getResponse();
        assertEquals("HTTP/1.1 405 Method Not Allowed", response.split("\r\n")[0]);
    }

}
