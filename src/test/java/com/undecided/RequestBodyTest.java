package com.undecided;

import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpVersion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/18/14.
 */
public class RequestBodyTest {
    @Test
    public void testParsePostRequestBodyWithSingleParamCorrectly() throws Exception {
        RequestBody requestBody = new RequestBody("home=Cosby");
        requestBody.parse();

        assertEquals("Cosby", requestBody.getParam("home"));
    }

    @Test
    public void testParsePostRequestBodyWithMultipleParamsCorrectly() throws Exception {
        RequestBody requestBody = new RequestBody("home=Cosby&work=Hollywood");
        requestBody.parse();

        assertEquals("Cosby", requestBody.getParam("home"));
        assertEquals("Hollywood", requestBody.getParam("work"));
    }

    @Test
    public void testAccessingInvalidParamWillReturnNull() throws Exception {
        RequestBody requestBody = new RequestBody("home=Cosby&work=Hollywood");
        requestBody.parse();

        assertEquals(null, requestBody.getParam("7060"));
    }
}
