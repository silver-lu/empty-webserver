package com.undecided.exceptions;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestRedirectRequiredException extends Exception {
    private static final long serialVersionUID = 6L;
    private String destination;

    public RequestRedirectRequiredException(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

}
