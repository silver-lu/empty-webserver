package com.undecided.validators;

import com.undecided.Request;
import com.undecided.RequestHeader;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestRestrictedMethodValidatorTest {
    @Test(expected = RequestMethodNotAllowedException.class)
    public void testUrlWithRestrictionsThrowRequestMethodNotAllowedException() throws Exception {
        Request request = new Request("POST /text-file.txt HTTP/1.1");
        request.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/text-file.txt", Arrays.asList(HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(request);
    }

    @Test(expected = RequestMethodNotAllowedException.class)
    public void testUrlWithMultipleRestrictionsThrowRequestMethodNotAllowedExceptionForPut() throws Exception {
        Request request = new Request("PUT /file1 HTTP/1.1");
        request.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(request);
    }

    @Test(expected = RequestMethodNotAllowedException.class)
    public void testUrlWithMultipleRestrictionsThrowRequestMethodNotAllowedExceptionForPost() throws Exception {
        Request request = new Request("POST /file1 HTTP/1.1");
        request.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(request);
    }

    @Test
    public void testUrlWithRestrictionsWillNotThrowExceptionForNoneRestrictedMethod() throws Exception {
        Request request = new Request("GET /file1 HTTP/1.1");
        request.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(request);
    }
}
