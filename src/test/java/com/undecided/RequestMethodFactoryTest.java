package com.undecided;

import com.undecided.handlers.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class RequestMethodFactoryTest {
    @Test
    public void testGetRequestAreRoutedToGetRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test HTTP/1.1");
        requestHeader.parse();

        HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
        assertEquals(HttpGetMethodHandler.class, factory.getHandler().getClass());
    }

    @Test
    public void testOptionsRequestAreRoutedToOptionsRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
        assertEquals(HttpOptionsMethodHandler.class, factory.getHandler().getClass());
    }

    @Test
    public void testPutRequestAreRoutedToPutRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /test HTTP/1.1");
        requestHeader.parse();

        HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
        assertEquals(HttpPutMethodHandler.class, factory.getHandler().getClass());
    }

    @Test
    public void testHeadRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("HEAD /test HTTP/1.1");
        requestHeader.parse();

        HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
        assertEquals(HttpHeadMethodHandler.class, factory.getHandler().getClass());
    }

    @Test
    public void testPostRequestAreRoutedToHeadRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("POST /test HTTP/1.1");
        requestHeader.parse();

        HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
        assertEquals(HttpPostMethodHandler.class, factory.getHandler().getClass());
    }
}
