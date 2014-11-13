package com.undecided;

import com.undecided.enums.HttpResponseCode;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
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
        String[] lines = header.split(System.lineSeparator());
        assertEquals("HTTP/1.1 400 Bad Request", lines[0]);
        assertEquals("Date: Tue, 11 Nov 2014 19:15:23 GMT", lines[1]);
        assertEquals("Server: undecided", lines[2]);
        assertEquals("Content-Type: text/html; charset=UTF-8", lines[3]);
        assertEquals("Content-Length: 0", lines[4]);
    }

    @Test
    public void testDefaultResponseReturnsEmptyBody() throws Exception {
        ServerResponse response = new ServerResponse();
        String body = response.getResponseBody();
        assertEquals("", body);
    }

    @Test
    public void testAllowedMethodsCanBeSet() throws Exception {
        ServerResponse response = new ServerResponse();
        response.setAllowedMethods(Arrays.asList("HEAD", "PUT", "GET"));
        assertTrue(response.getResponseHeader().contains("Allow: HEAD,PUT,GET"));
    }
}
