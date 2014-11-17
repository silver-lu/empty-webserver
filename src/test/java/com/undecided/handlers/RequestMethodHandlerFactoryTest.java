package com.undecided.handlers;

import com.undecided.RequestHeader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class RequestMethodHandlerFactoryTest {
    @Test
    public void testGetRequestAreRoutedToGetRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpGetMethodHandler.class, HttpMethodHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testOptionsRequestAreRoutedToOptionsRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpOptionsMethodHandler.class, HttpMethodHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testPutRequestAreRoutedToPutRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpPutMethodHandler.class, HttpMethodHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testHeadRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("HEAD /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpHeadMethodHandler.class, HttpMethodHandlerFactory.getInstance(requestHeader).getClass());
    }

    @Test
    public void testPostRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("POST /test HTTP/1.1");
        requestHeader.parse();

        assertEquals(HttpPostMethodHandler.class, HttpMethodHandlerFactory.getInstance(requestHeader).getClass());
    }
}
