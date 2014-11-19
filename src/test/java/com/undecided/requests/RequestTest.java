package com.undecided.requests;

import com.undecided.enums.HttpRequestMethod;
import com.undecided.requests.Request;
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

    @Test
    public void testParsingOfAPatchRequestGivenARawRequest() throws Exception {
        String rawRequest = "PATCH /pom.xml HTTP/1.1" + System.lineSeparator();
        rawRequest += "Host: www.example.com" + System.lineSeparator();
        rawRequest += "Content-Type: application/example" + System.lineSeparator();
        rawRequest += "If-Match: \"e0023aa4e\"" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

        Request request = new Request(rawRequest);
        request.parse();

        assertEquals(HttpRequestMethod.Patch, request.getRequestHeader().getRequestMethod());
    }
}
