package com.undecided.exceptions;

import java.util.List;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestMethodNotAllowedException extends Exception {
    private static final long serialVersionUID = 7L;
    private List<String> allowedMethods;

    public RequestMethodNotAllowedException(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedMethods() {
        return allowedMethods;
    }
}
