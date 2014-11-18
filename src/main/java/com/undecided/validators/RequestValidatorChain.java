package com.undecided.validators;

import com.undecided.Request;
import com.undecided.RequestHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestValidatorChain {
    private List<RequestValidator> validators;

    public RequestValidatorChain() {
        validators = new ArrayList<RequestValidator>();
    }

    public List<RequestValidator> getValidators() {
        return validators;
    }

    public void add(RequestValidator validator) {
        validators.add(validator);
    }

    public void validateChain(Request request) throws Exception {
        for(RequestValidator validator : validators) {
            validator.validate(request);
        }
    }

}
