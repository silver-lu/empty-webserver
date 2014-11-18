package com.undecided.handlers.requestmethod;

import com.undecided.RequestHeader;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.*;
import com.undecided.enums.HttpResponseCode;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPutMethodHandler extends HttpHandler {
    public HttpPutMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Ok);
        response = serverResponse;
    }
}
