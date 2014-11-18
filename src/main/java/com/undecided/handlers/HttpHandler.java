package com.undecided.handlers;

import com.undecided.Request;
import com.undecided.RequestHeader;
import com.undecided.responses.ServerResponse;

/**
 * Created by silver.lu on 11/12/14.
 */
public abstract class HttpHandler {

    protected ServerResponse response;
    protected Request request;

    public HttpHandler(Request request){
        this.request = request;
    }

    public abstract void processRequest() throws Exception;

    public ServerResponse getResponse() {
        return response;
    }
}
