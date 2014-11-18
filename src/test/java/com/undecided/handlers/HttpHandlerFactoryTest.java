package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.handlers.requestmethod.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpHandlerFactoryTest {
    @Test
    public void testGetRequestAreRoutedToGetRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpGetMethodHandler.class, HttpHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testOptionsRequestAreRoutedToOptionsRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpOptionsMethodHandler.class, HttpHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testPutRequestAreRoutedToPutRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpPutMethodHandler.class, HttpHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testHeadRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("HEAD /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpHeadMethodHandler.class, HttpHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testPostRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("POST /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpPostMethodHandler.class, HttpHandlerFactory.getInstance(requestHeader).getClass());
    }
}
