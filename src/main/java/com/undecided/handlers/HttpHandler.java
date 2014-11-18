package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.responses.ServerResponse;

/**
 * Created by silver.lu on 11/12/14.
 */
public abstract class HttpHandler {

    protected ServerResponse response;
    protected RequestHeader requestHeader;

    public HttpHandler(RequestHeader requestHeader){
        this.requestHeader = requestHeader;
    }

    public abstract void processRequest() throws Exception;

    public ServerResponse getResponse() {
        return response;
    }
}
