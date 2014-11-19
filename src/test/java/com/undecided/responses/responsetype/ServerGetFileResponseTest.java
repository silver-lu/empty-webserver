package com.undecided.responses.responsetype;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.responsetype.ServerGetFileResponse;
import org.junit.Test;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/13/14.
 */
public class ServerGetFileResponseTest {
    @Test
    public void testResponseIsLoadedWithCorrectMimeTypeInTheHeader() throws Exception {
        ServerResponse response = new ServerGetFileResponse();
        response.setContentType("text/xml");
        assertTrue(response.getHeader().contains("Content-Type: text/xml"));
    }

    @Test
    public void testResponseReturnedIsOfResponseCode200() throws Exception {
        ServerResponse response = new ServerGetFileResponse();
        assertEquals(HttpResponseCode.Ok, response.getResponseCode());
        assertEquals("HTTP/1.1 200 OK", response.getHeader().split(System.lineSeparator())[0]);
    }
}
