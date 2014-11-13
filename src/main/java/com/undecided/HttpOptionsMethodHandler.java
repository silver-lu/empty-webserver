package com.undecided;

import java.util.ArrayList;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpOptionsMethodHandler extends HttpMethodHandler {

    public HttpOptionsMethodHandler(RequestHeader requestHandler) {
        super(requestHandler);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
        serverResponse.setAllowedMethods(new ArrayList<String>(HttpConstant.REQUEST_METHODS.keySet()));
        response = serverResponse.getHttpResponse();
    }
}
