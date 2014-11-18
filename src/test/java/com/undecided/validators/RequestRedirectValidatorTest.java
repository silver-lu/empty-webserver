package com.undecided.validators;

import com.undecided.requests.Request;
import com.undecided.exceptions.RequestRedirectRequiredException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestRedirectValidatorTest {

    @Test(expected = RequestRedirectRequiredException.class)
    public void testConfiguredRedirectUrlsWillThrowRedirectRequiredException() throws Exception {
        Request request = new Request("GET /redirect HTTP/1.1");
        request.parse();

        Map<String, String> redirectConfig = new HashMap<String, String>() {
            {
                put("/redirect", "http://localhost:5000/");
            }
        };

        RequestValidator validator = new RequestRedirectValidator(redirectConfig);
        validator.validate(request);
    }

    @Test
    public void testRedirectRequiredExceptionIsSetWithRedirectDestination() throws Exception {
        Request request = new Request("GET /redirect HTTP/1.1");
        request.parse();

        Map<String, String> redirectConfig = new HashMap<String, String>() {
            {
                put("/redirect", "http://localhost:5000/");
            }
        };

        RequestValidator validator = new RequestRedirectValidator(redirectConfig);
        try {
            validator.validate(request);
        }
        catch ( RequestRedirectRequiredException expected) {
            assertEquals("http://localhost:5000/", expected.getDestination());
        }
    }

    @Test
    public void testNotConfiguredRedirectUrlsWillNotThrowRedirectRequiredException() throws Exception {
        Request request = new Request("GET /dontredirect HTTP/1.1");
        request.parse();

        Map<String, String> redirectConfig = new HashMap<String, String>() {
            {
                put("/redirect", "http://localhost:5000/");
            }
        };

        RequestValidator validator = new RequestRedirectValidator(redirectConfig);
        validator.validate(request);
    }
}
