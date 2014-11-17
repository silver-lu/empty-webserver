package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.responses.ServerResponse;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerStandardResponse;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPostMethodHandler extends HttpMethodHandler {
    public HttpPostMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Ok);
        response = serverResponse;
    }
}
