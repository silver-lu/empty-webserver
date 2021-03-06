package com.undecided.handlers.requestmethod;

import com.undecided.requests.Request;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.requestmethod.ServerOptionsResponse;
import com.undecided.responses.ServerResponse;
import com.undecided.constants.HttpConstant;

import java.util.ArrayList;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpOptionsMethodHandler extends HttpHandler {

    public HttpOptionsMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerOptionsResponse();
        serverResponse.setAllowedMethods(new ArrayList<String>(HttpConstant.REQUEST_METHODS.keySet()));
        response = serverResponse;
    }
}
