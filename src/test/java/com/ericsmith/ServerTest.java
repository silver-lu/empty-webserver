package com.ericsmith;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void testInvalidRequestWillReturn400() throws Exception {
        Server server = new Server();
        String response = server.processRequest("");
        assertEquals("HTTP/1.1 400 Bad Request", response);
    }

    @Test
    public void testValidRequestWillReturn404ByDefault() throws Exception {
        Server server = new Server();
        String response = server.processRequest("GET /test HTTP/1.1");
        assertEquals("HTTP/1.1 404 Not Found", response);
    }
}
