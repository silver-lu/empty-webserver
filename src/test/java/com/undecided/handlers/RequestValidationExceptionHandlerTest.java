package com.undecided.handlers;

import com.undecided.Request;
import com.undecided.RequestHeader;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import com.undecided.exceptions.RequestRedirectRequiredException;
import com.undecided.responses.ServerResponse;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestValidationExceptionHandlerTest {
    @Test
    public void testHandlerReturnsCorrectResponseWhenEncounteringARedirectRequiredException() throws Exception {
        Request request = new Request("GET /redirect-test HTTP/1.1");
        request.parse();

        RequestValidationExceptionHandler validationExceptionHandler = new RequestValidationExceptionHandler(request, new RequestRedirectRequiredException("http://localhost:5000/"));
        validationExceptionHandler.processException();
        ServerResponse response = validationExceptionHandler.getResponse();

        assertEquals(HttpResponseCode.Redirect, response.getResponseCode());
        assertTrue(response.getHeader().contains("Location: http://localhost:5000/"));
    }

    @Test
    public void testHandlerReturnsCorrectResponseWhenEncounteringAMethodNotAllowedException() throws Exception {
        Request request = new Request("GET /redirect-test HTTP/1.1");
        request.parse();

        RequestValidationExceptionHandler validationExceptionHandler = new RequestValidationExceptionHandler(request, new RequestMethodNotAllowedException(Arrays.asList("GET","HEAD","OPTIONS")));
        validationExceptionHandler.processException();
        ServerResponse response = validationExceptionHandler.getResponse();

        assertEquals(HttpResponseCode.MethodNotAllowed, response.getResponseCode());
        assertTrue(response.getHeader().contains("Allow: GET,HEAD,OPTIONS"));
    }
}
