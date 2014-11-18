package com.undecided.validators;

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
        RequestHeader requestHeader = new RequestHeader("POST /text-file.txt HTTP/1.1");
        requestHeader.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/text-file.txt", Arrays.asList(HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(requestHeader);
    }

    @Test(expected = RequestMethodNotAllowedException.class)
    public void testUrlWithMultipleRestrictionsThrowRequestMethodNotAllowedExceptionForPut() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /file1 HTTP/1.1");
        requestHeader.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(requestHeader);
    }

    @Test(expected = RequestMethodNotAllowedException.class)
    public void testUrlWithMultipleRestrictionsThrowRequestMethodNotAllowedExceptionForPost() throws Exception {
        RequestHeader requestHeader = new RequestHeader("POST /file1 HTTP/1.1");
        requestHeader.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(requestHeader);
    }

    @Test
    public void testUrlWithRestrictionsWillNotThrowExceptionForNoneRestrictedMethod() throws Exception {
        RequestHeader requestHeader = new RequestHeader("GET /file1 HTTP/1.1");
        requestHeader.parse();

        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        RequestValidator validator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
        validator.validate(requestHeader);
    }
}
