package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class RequestMethodRouterTest {
    @Test
    public void testGetRequestAreRoutedToGetRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /test HTTP/1.1");
        requestHeader.parse();

        RequestMethodRouter router = new RequestMethodRouter(requestHeader);
        assertEquals(HttpGetMethodHandler.class, router.getHandler().getClass());
    }

    @Test
    public void testOptionsRequestAreRoutedToOptionsRequestHandler() throws Exception {
        RequestHeader requestHeader = new RequestHeader("OPTIONS * HTTP/1.1");
        requestHeader.parse();

        RequestMethodRouter router = new RequestMethodRouter(requestHeader);
        assertEquals(HttpOptionsMethodHandler.class, router.getHandler().getClass());
    }
}
