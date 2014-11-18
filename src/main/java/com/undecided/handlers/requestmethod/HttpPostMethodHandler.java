package com.undecided.handlers.requestmethod;

import com.undecided.RequestHeader;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.ServerResponse;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerStandardResponse;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPostMethodHandler extends HttpHandler {
    public HttpPostMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Ok);
        response = serverResponse;
    }
}