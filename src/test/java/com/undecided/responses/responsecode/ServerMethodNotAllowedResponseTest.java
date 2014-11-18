package com.undecided.responses.responsecode;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.requestmethod.ServerOptionsResponse;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerMethodNotAllowedResponseTest {
    @Test
    public void testRedirectResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerMethodNotAllowedResponse();
        response.setAllowedMethods(Arrays.asList("HEAD", "PUT", "GET"));
        assertEquals(HttpResponseCode.MethodNotAllowed, response.getResponseCode());
        assertEquals("HTTP/1.1 405 Method Not Allowed", response.getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testOptionsResponseHasTheAllowedMethodsReturned() throws Exception {
        ServerResponse response = new ServerMethodNotAllowedResponse();
        response.setAllowedMethods(Arrays.asList("HEAD", "PUT", "GET"));
        assertTrue(response.getHeader().contains("Allow: HEAD,PUT,GET"));
    }
}
