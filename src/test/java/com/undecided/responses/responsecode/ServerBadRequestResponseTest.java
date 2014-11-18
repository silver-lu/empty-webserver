package com.undecided.responses.responsecode;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerBadRequestResponseTest {
    @Test
    public void testRedirectResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerBadRequestResponse();
        assertEquals(HttpResponseCode.BadRequest, response.getResponseCode());
        assertEquals("HTTP/1.1 400 Bad Request", response.getHeader().split(System.lineSeparator())[0]);
    }
}
