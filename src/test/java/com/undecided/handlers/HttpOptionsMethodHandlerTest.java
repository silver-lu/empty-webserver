package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.handlers.requestmethod.HttpOptionsMethodHandler;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpOptionsMethodHandlerTest {

    @Test
    public void testCorrectResponseCodeIsReturnedByDefault() throws Exception {
        HttpOptionsMethodHandler handler = new HttpOptionsMethodHandler(new RequestHeader("OPTIONS * HTTP/1.1"));
        handler.processRequest();
        assertEquals("HTTP/1.1 200 OK", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testAllowedKeywordIsReturnedByDefault() throws Exception {
        HttpOptionsMethodHandler handler = new HttpOptionsMethodHandler(new RequestHeader("OPTIONS * HTTP/1.1"));
        handler.processRequest();
        assertTrue(handler.getResponse().getHeader().contains("Allow:"));
    }
}
