package com.undecided;

import com.undecided.exceptions.MalformedRequestException;
import com.undecided.exceptions.ProtocolNotRecognizedException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/10/14.
 */
public class ClientRequestTest {
    @Test
    public void testParseGetRequestCorrectly() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET /test.html HTTP/1.1");
        clientRequest.parse();

        assertEquals(HttpRequestMethod.Get, clientRequest.getRequestMethod());
        assertEquals("/test.html", clientRequest.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, clientRequest.getHttpVersion());
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenRequestLineIsEmpty() throws Exception {
        ClientRequest clientRequest = new ClientRequest("");
        clientRequest.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenThereAreLessThanTwoSpaceTokens() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET");
        clientRequest.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenThereAreMoreThanTwoSpaceTokens() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET /abc 123.html HTTP/1.1");
        clientRequest.parse();
    }

    @Test(expected = RequestMethodNotRecognizedException.class)
    public void testRequestMethodNotRecognizedExceptionIsThrownWhenRequestMethodIsNotRecognized() throws Exception {
        ClientRequest clientRequest = new ClientRequest("UNKNOWN /abc HTTP/1.1");
        clientRequest.parse();
    }

    @Test(expected = ProtocolNotRecognizedException.class)
    public void testProtocolNotRecognizedExceptionIsThrownWhenRequestMethodIsNotRecognized() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET /abc HTTP/9.1");
        clientRequest.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionIsThrownWhenRequestUrlIsMissing() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET  HTTP/1.1");
        clientRequest.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionIsThrownWhenRequestUrlMissingSlash() throws Exception {
        ClientRequest clientRequest = new ClientRequest("GET abc HTTP/1.1");
        clientRequest.parse();
    }

}
