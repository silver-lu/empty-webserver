package com.undecided.validators;

import com.undecided.requests.Request;

/**
 * Created by silver.lu on 11/17/14.
 */
public interface RequestValidator {
    public void validate(Request requestHeader) throws Exception;
}
