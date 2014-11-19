package com.undecided.responses.responsetype;

import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/18/14.
 */
public class ServerPatchFileResponseTest {
    @Test
    public void testResponseReturnedIsOfResponseCode204() throws Exception {
        ServerResponse response = new ServerPatchFileResponse();
        assertEquals(HttpResponseCode.NoContent, response.getResponseCode());
        assertEquals("HTTP/1.1 204 No Content", response.getHeader().split(System.lineSeparator())[0]);
    }

    @Test
    public void testResponseReturnedCanBeSetWithAnETagParameter() throws Exception {
        ServerResponse response = new ServerPatchFileResponse();
        response.setETag("abc123");

        assertTrue(response.getHeader().contains("ETag: abc123"));
    }
}
