package com.undecided;

import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpVersion;
import com.undecided.exceptions.MalformedRequestException;
import com.undecided.exceptions.ProtocolNotRecognizedException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/10/14.
 */
public class ClientRequestHeaderTest {
    @Test
    public void testParseGetRequestCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test.html HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Get, requestHeader.getRequestMethod());
        assertEquals("/test.html", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());
    }

    @Test
    public void testParseOptionsRequestCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Options, requestHeader.getRequestMethod());
        assertEquals("*", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());

    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenRequestLineIsEmpty() throws Exception {
        RequestHeader requestHeader = new RequestHeader("");
        requestHeader.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenThereAreLessThanTwoSpaceTokens() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET");
        requestHeader.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionWhenThereAreMoreThanTwoSpaceTokens() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /abc 123.html HTTP/1.1");
        requestHeader.parse();
    }

    @Test(expected = RequestMethodNotRecognizedException.class)
    public void testRequestMethodNotRecognizedExceptionIsThrownWhenRequestMethodIsNotRecognized() throws Exception {
        RequestHeader requestHeader = new RequestHeader("UNKNOWN /abc HTTP/1.1");
        requestHeader.parse();
    }

    @Test(expected = ProtocolNotRecognizedException.class)
    public void testProtocolNotRecognizedExceptionIsThrownWhenRequestMethodIsNotRecognized() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /abc HTTP/9.1");
        requestHeader.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionIsThrownWhenRequestUrlIsMissing() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET  HTTP/1.1");
        requestHeader.parse();
    }

    @Test(expected = MalformedRequestException.class)
    public void testMalformedRequestExceptionIsThrownWhenRequestUrlMissingSlash() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET abc HTTP/1.1");
        requestHeader.parse();
    }

}
