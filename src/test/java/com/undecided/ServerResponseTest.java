package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponseTest {
    @Test
    public void testDefaultResponseIsSetTo400() throws Exception {
        ServerResponse response = new ServerResponse();
        assertEquals(HttpResponseCode.BadRequest, response.getResponseCode());
    }
}
