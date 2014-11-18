package com.undecided.responses.requestmethod;

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
public class ServerOptionsResponseTest {
    @Test
    public void testOptionsResponseHasTheCorrectResponseCode() throws Exception {
        ServerResponse response = new ServerOptionsResponse();
        response.setAllowedMethods(Arrays.asList("HEAD", "PUT", "GET"));
        assertEquals(HttpResponseCode.Ok, response.getResponseCode());
        assertEquals("HTTP/1.1 200 OK", response.getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testOptionsResponseHasTheAllowedMethodsReturned() throws Exception {
        ServerResponse response = new ServerOptionsResponse();
        response.setAllowedMethods(Arrays.asList("HEAD", "PUT", "GET"));
        assertTrue(response.getHeader().contains("Allow: HEAD,PUT,GET"));
    }
}
