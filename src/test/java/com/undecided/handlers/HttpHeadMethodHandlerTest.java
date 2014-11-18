package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.requestmethod.HttpGetMethodHandler;
import com.undecided.handlers.requestmethod.HttpHeadMethodHandler;
import org.junit.Before;
import org.junit.Test;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by anthony.chai on 11/18/14.
 */
public class HttpHeadMethodHandlerTest {

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }

    @Test
    public void testOnlyResponseWithHeader200() throws Exception {
        RequestHeader requestHeader = new RequestHeader("HEAD /src HTTP/1.1");
        requestHeader.parse();
        HttpHeadMethodHandler handler = new HttpHeadMethodHandler(requestHeader);
        handler.processRequest();
        assertEquals("HTTP/1.1 200 OK", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
        assertEquals(null, (handler.getResponse().getBody()));
    }
}
