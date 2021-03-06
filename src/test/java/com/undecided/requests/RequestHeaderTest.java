package com.undecided.requests;

import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpSupportedHeader;
import com.undecided.enums.HttpVersion;
import com.undecided.exceptions.MalformedRequestException;
import com.undecided.exceptions.ProtocolNotRecognizedException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import com.undecided.requests.RequestHeader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/10/14.
 */
public class RequestHeaderTest {
    @Test
    public void testParseGetRequestHeaderCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test.html HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Get, requestHeader.getRequestMethod());
        assertEquals("/test.html", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());
    }

    @Test
    public void testParseOptionsRequestHeaderCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Options, requestHeader.getRequestMethod());
        assertEquals("*", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());
    }

    @Test
    public void testParsePutRequestHeaderCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /abc HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Put, requestHeader.getRequestMethod());
        assertEquals("/abc", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());
    }

    @Test
    public void testParseHeadRequestHeaderCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("HEAD /abc HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Head, requestHeader.getRequestMethod());
        assertEquals("/abc", requestHeader.getRequestUrl());
        assertEquals(HttpVersion.OneDotOne, requestHeader.getHttpVersion());
    }

    @Test
    public void testParsePostRequestHeaderCorrectly() throws Exception {
        RequestHeader requestHeader = new RequestHeader("POST /abc HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpRequestMethod.Post, requestHeader.getRequestMethod());
        assertEquals("/abc", requestHeader.getRequestUrl());
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


    @Test
    public void testThatETagDirectiveIfMatchInTheHeaderCanBeRead() throws Exception {
        String rawRequest = "PATCH /file.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "Host: www.example.com" + System.lineSeparator();
        rawRequest += "Content-Type: application/example" + System.lineSeparator();
        rawRequest += "If-Match: \"e0023aa4e\"" + System.lineSeparator();
        rawRequest += "Content-Length: 20";

        RequestHeader requestHeader = new RequestHeader(rawRequest);
        requestHeader.parse();
        assertEquals("\"e0023aa4e\"", requestHeader.getHeaderParam(HttpSupportedHeader.ETag));
    }

}
