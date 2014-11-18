package com.undecided.responses;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerRedirectResponse;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/13/14.
 */
public class ServerRedirectResponseTest {
    @Test
    public void testRedirectResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerRedirectResponse();
        response.setRedirectLocation("/");
        assertEquals(HttpResponseCode.Redirect, response.getResponseCode());
        assertEquals("HTTP/1.1 302 Redirect", response.getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testREdirectResponseHasANewLocationSet() throws Exception {
        ServerResponse response = new ServerRedirectResponse();
        response.setRedirectLocation("/");
        assertEquals("Location: /", response.getHeader().split(System.lineSeparator())[1]);
    }
}
