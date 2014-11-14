package com.undecided;

import com.undecided.enums.HttpResponseCode;
import com.undecided.handlers.HttpGetMethodHandler;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/13/14.
 */
public class ServerGetFileResponseTest {
    @Test
    public void testResponseIsLoadedWithCorrectMimeTypeInTheHeader() throws Exception {
        ServerResponse response = new ServerGetFileResponse(HttpResponseCode.Ok);
        response.setContentType("text/xml");
        assertTrue(response.getHeader().contains("Content-Type: text/xml"));
    }
}
