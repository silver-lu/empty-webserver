package com.undecided.validators;

import com.undecided.requests.Request;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import com.undecided.exceptions.RequestRedirectRequiredException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestValidatorChainTest {
    RequestValidator redirectValidator;
    RequestValidator restrictedMethodValidator;

    @Before
    public void setUp() throws Exception {
        Map<String, String> redirectConfig = new HashMap<String, String>() {
            {
                put("/redirect", "http://localhost:5000/");
            }
        };

        redirectValidator = new RequestRedirectValidator(redirectConfig);


        Map<String, List<HttpRequestMethod>> restrictedMethodConfig = new HashMap<String, List<HttpRequestMethod>>() {
            {
                put("/file1", Arrays.asList(HttpRequestMethod.Put, HttpRequestMethod.Post));
            }
        };

        restrictedMethodValidator = new RequestRestrictedMethodValidator(restrictedMethodConfig);
    }

    @Test
    public void testValidatorChainStartOutWithoutAnyValidator(){
        RequestValidatorChain chain = new RequestValidatorChain();
        assertEquals(0, chain.getValidators().size());
    }

    @Test
    public void testWeCanAddNewValidatorsToTheChain() throws Exception {
        RequestValidatorChain chain = new RequestValidatorChain();

        chain.add(redirectValidator);
        assertEquals(1, chain.getValidators().size());
    }

    @Test(expected = RequestRedirectRequiredException.class)
    public void testExceptionIsThrownWhenTheFirstValidatorFailToValidate() throws Exception {
        RequestValidatorChain chain = new RequestValidatorChain();

        Request request = new Request("GET /redirect HTTP/1.1");
        request.parse();

        chain.add(redirectValidator);
        chain.add(restrictedMethodValidator);
        chain.validateChain(request);
    }

    @Test(expected = RequestMethodNotAllowedException.class)
    public void testExceptionIsThrownWhenTheSecondValidatorFailToValidate() throws Exception {
        RequestValidatorChain chain = new RequestValidatorChain();

        Request request = new Request("POST /file1 HTTP/1.1");
        request.parse();

        chain.add(redirectValidator);
        chain.add(restrictedMethodValidator);
        chain.validateChain(request);
    }

}
