package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponseTest {
    @Test
    public void testDefaultResponseIsSetTo400() throws Exception {
        ServerResponse response = new ServerResponse();
        assertEquals(HttpResponseCode.BadRequest, response.getResponseCode());
    }

    @Test
    public void testResponseCodeCanBeSetViaConstructorParams() throws Exception {
        ServerResponse response = new ServerResponse(HttpResponseCode.NotFound);
        assertEquals(HttpResponseCode.NotFound, response.getResponseCode());
    }

    @Test
    public void testDefaultResponseHasServerTypeSet() throws Exception {
        ServerResponse response = new ServerResponse();
        assertEquals("undecided", response.getServerType());
    }

    @Test
    public void testDefaultResponseHasContentTypeSet() throws Exception {
        ServerResponse response = new ServerResponse();
        assertEquals("text/html", response.getContentType());
    }

    @Test
    public void testDefaultResponseHasCharsetSet() throws Exception {
        ServerResponse response = new ServerResponse();
        assertEquals("UTF-8", response.getCharSet());
    }

    @Test
    public void testContentLengthIsAutomaticallyCalculated() throws Exception {
        ServerResponse response = new ServerResponse();
        response.setResponseBody("This is a Test Body");
        assertEquals(19, response.getContentLength());
    }

    @Test
    public void testDefaultResponseReturnCorrectHeader() throws Exception {
        ServerResponse response = new ServerResponse();
        String header = response.getResponseHeader();
    }

    @Test
    public void testDefaultResponseReturnsEmptyBody() throws Exception {
        ServerResponse response = new ServerResponse();
        String body = response.getResponseBody();
    }
}