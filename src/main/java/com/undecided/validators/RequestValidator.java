package com.undecided.validators;

import com.undecided.RequestHeader;

/**
 * Created by silver.lu on 11/17/14.
 */
public interface RequestValidator {
    public void validate(RequestHeader requestHeader) throws Exception;
}
