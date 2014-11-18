package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.constants.Configurations;
import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import com.undecided.exceptions.RequestRedirectRequiredException;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.validators.RequestRedirectValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestValidationExceptionHandler {

    private Configurations config;
    private RequestHeader requestHeader;
    private Exception exception;
    private ServerResponse response;

    public RequestValidationExceptionHandler(RequestHeader requestHeader, Exception exception) {
        this.requestHeader = requestHeader;
        this.exception = exception;
        this.config = config;
    }

    public void processException() {
        if ( exception.getClass().equals(RequestRedirectRequiredException.class)) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Redirect);
            RequestRedirectRequiredException redirectRequiredException = (RequestRedirectRequiredException) exception;
            serverResponse.setRedirectLocation(redirectRequiredException.getDestination());
            response = serverResponse;
        }
        else if ( exception.getClass().equals(RequestMethodNotAllowedException.class)) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.MethodNotAllowed);
            RequestMethodNotAllowedException methodNotAllowedException = (RequestMethodNotAllowedException) exception;
            serverResponse.setAllowedMethods(methodNotAllowedException.getAllowedMethods());
            response = serverResponse;
        }
        else {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.BadRequest);
            response = serverResponse;
        }
    }

    public ServerResponse getResponse() {
        return response;
    }
}
