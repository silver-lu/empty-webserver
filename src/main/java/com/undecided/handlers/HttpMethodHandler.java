package com.undecided.handlers;

import com.undecided.RequestHeader;

/**
 * Created by silver.lu on 11/12/14.
 */
public abstract class HttpMethodHandler {

    protected String response;
    protected RequestHeader requestHeader;

    public HttpMethodHandler(RequestHeader requestHeader){
        this.requestHeader = requestHeader;
        this.response = "";
    }

    public abstract void processRequest();

    public String getResponse() {
        return response;
    }
}
