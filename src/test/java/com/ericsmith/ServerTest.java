package com.ericsmith;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerTest {

    @Test
    public void testProcessRequestReturn404ByDefault() throws Exception {
        Server server = new Server();
        String response = server.processRequest("");
        assertEquals("HTTP/1.1 404 Not Found", response);
    }
}
