package com.undecided.responses.responsecode;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/19/14.
 */
public class ServerPartialContentResponseTest {
    @Test
    public void testRedirectResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerPartialContentResponse();
        assertEquals(HttpResponseCode.PartialContent, response.getResponseCode());
        assertEquals("HTTP/1.1 206 Partial Content", response.getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testReturnedContentIsOfRangeSpecified() throws Exception {
        ServerResponse response = new ServerPartialContentResponse();
        response.setRange("byte=0-4");
        response.setResponseBody("abcdefghijklmn".getBytes());
        assertEquals("abcd", new String(response.getBody()));
    }
}
