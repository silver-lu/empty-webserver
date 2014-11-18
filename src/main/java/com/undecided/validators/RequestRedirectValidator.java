package com.undecided.validators;

import com.undecided.RequestHeader;
import com.undecided.exceptions.RequestRedirectRequiredException;

import java.util.Map;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestRedirectValidator implements RequestValidator {
    private Map<String, String> redirectConfig;

    public RequestRedirectValidator(Map<String, String> redirectConfig) {
        this.redirectConfig = redirectConfig;
    }

    @Override
    public void validate(RequestHeader requestHeader) throws RequestRedirectRequiredException {
        if (redirectConfig.containsKey(requestHeader.getRequestUrl())) {
            throw new RequestRedirectRequiredException(redirectConfig.get(requestHeader.getRequestUrl()));
        }
    }
}
