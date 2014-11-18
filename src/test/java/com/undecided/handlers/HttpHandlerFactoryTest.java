package com.undecided.handlers;

import com.undecided.requests.Request;
import com.undecided.handlers.requestmethod.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpHandlerFactoryTest {
    @Test
    public void testGetRequestAreRoutedToGetRequestHandler() throws Exception {
        Request request = new Request("GET /test HTTP/1.1");
        request.parse();

        assertEquals(HttpGetMethodHandler.class, HttpHandlerFactory.getInstance(request).getClass());
    }

    @Test
    public void testOptionsRequestAreRoutedToOptionsRequestHandler() throws Exception {
        Request request = new Request("OPTIONS * HTTP/1.1");
        request.parse();

        assertEquals(HttpOptionsMethodHandler.class, HttpHandlerFactory.getInstance(request).getClass());
    }

    @Test
    public void testPutRequestAreRoutedToPutRequestHandler() throws Exception {
        Request request = new Request("PUT /test HTTP/1.1");
        request.parse();

        assertEquals(HttpPutMethodHandler.class, HttpHandlerFactory.getInstance(request).getClass());
    }

    @Test
    public void testHeadRequestAreRoutedToHeadRequestHandler() throws Exception {
        Request request = new Request("HEAD /test HTTP/1.1");
        request.parse();

        assertEquals(HttpHeadMethodHandler.class, HttpHandlerFactory.getInstance(request).getClass());
    }

    @Test
    public void testPostRequestAreRoutedToHeadRequestHandler() throws Exception {
        Request request = new Request("POST /test HTTP/1.1");
        request.parse();

        assertEquals(HttpPostMethodHandler.class, HttpHandlerFactory.getInstance(request).getClass());
    }
}
