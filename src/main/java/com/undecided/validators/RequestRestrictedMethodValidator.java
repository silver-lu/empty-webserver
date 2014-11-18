package com.undecided.validators;

import com.undecided.RequestHeader;
import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import com.undecided.exceptions.RequestRedirectRequiredException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestRestrictedMethodValidator implements RequestValidator {
    private Map<String, List<HttpRequestMethod>> restrictedMethodConfig;

    public RequestRestrictedMethodValidator(Map<String, List<HttpRequestMethod>> restrictedMethodConfig) {
        this.restrictedMethodConfig = restrictedMethodConfig;
    }

    @Override
    public void validate(RequestHeader requestHeader) throws RequestMethodNotAllowedException {
        if (restrictedMethodConfig.containsKey(requestHeader.getRequestUrl())) {
            List<HttpRequestMethod> restrictedMethods = restrictedMethodConfig.get(requestHeader.getRequestUrl());
            if ( restrictedMethods.contains(requestHeader.getRequestMethod())) {
                throw new RequestMethodNotAllowedException(getAllowedMethods(requestHeader.getRequestUrl()));
            }
        }
    }

    private List<String> getAllowedMethods(String url) {
        List<String> defaultAllowedMethods = new ArrayList<String>(HttpConstant.REQUEST_METHODS.keySet());

        for(HttpRequestMethod method: restrictedMethodConfig.get(url)) {
            defaultAllowedMethods.remove(HttpConstant.METHOD_STRINGS.get(method));
        }

        return defaultAllowedMethods;
    }
}
