package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.ServerResponse;
import com.undecided.enums.HttpResponseCode;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPostMethodHandler extends HttpMethodHandler {
    public HttpPostMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
        response = serverResponse.getHttpResponse();
    }
}
