package com.undecided.handlers;

import com.undecided.requests.Request;
import com.undecided.constants.Configurations;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.RequestMethodNotAllowedException;
import com.undecided.exceptions.RequestRedirectRequiredException;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;

/**
 * Created by silver.lu on 11/17/14.
 */
public class RequestValidationExceptionHandler {

    private Configurations config;
    private Request request;
    private Exception exception;
    private ServerResponse response;

    public RequestValidationExceptionHandler(Request request, Exception exception) {
        this.request = request;
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
