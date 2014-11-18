package com.undecided.responses.responsecode;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerNotFoundResponseTest {
    @Test
    public void testRedirectResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerNotFoundResponse();
        assertEquals(HttpResponseCode.NotFound, response.getResponseCode());
        assertEquals("HTTP/1.1 404 Not Found", response.getHeader().split(System.lineSeparator())[0]);
    }
}
