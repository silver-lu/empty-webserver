package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.ServerResponse;

/**
 * Created by silver.lu on 11/12/14.
 */
public abstract class HttpMethodHandler {

    protected ServerResponse response;
    protected RequestHeader requestHeader;

    public HttpMethodHandler(RequestHeader requestHeader){
        this.requestHeader = requestHeader;
    }

    public abstract void processRequest();

    public ServerResponse getResponse() {
        return response;
    }
}
