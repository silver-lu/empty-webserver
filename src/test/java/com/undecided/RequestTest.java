package com.undecided;

import com.undecided.enums.HttpRequestMethod;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/18/14.
 */
public class RequestTest {
    @Test
    public void testRequestObjectHasAHeaderAndABodyObjectGivenAStringRequest() throws Exception {
        String input = "";
        input += "POST /test HTTP/1.1" + System.lineSeparator();
        input += "User-Agent: HTTPTool/1.0" + System.lineSeparator();
        input += "Content-Type: application/x-www-form-urlencoded" + System.lineSeparator();
        input += "Content-Length: 32" + System.lineSeparator();
        input += System.lineSeparator();
        input += "home=Cosby&favorite+flavor=flies";


        Request request = new Request(input);
        request.parse();

        assertEquals(HttpRequestMethod.Post, request.getRequestHeader().getRequestMethod());
        assertEquals("Cosby", request.getRequestBody().getParam("home"));
    }

}
