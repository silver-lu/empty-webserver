package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.exceptions.MissingRequestHeaderException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import com.undecided.handlers.HttpMethodHandler;
import com.undecided.handlers.HttpMethodHandlerFactory;
import com.undecided.responses.ServerBadRequestResponse;
import com.undecided.responses.ServerMethodNotAllowedResponse;
import com.undecided.responses.ServerResponse;

import java.util.ArrayList;

/**
 * Created by silver.lu on 11/11/14.
 */
public class RequestHandler {
    RequestHeader requestHeader;
    ServerResponse response;

    public RequestHandler(String request) {
        this.requestHeader = new RequestHeader(request);
    }

    public RequestHandler() {

    }

    public void processRequest() throws MissingRequestHeaderException {
        if (requestHeader == null) {
            throw new MissingRequestHeaderException();
        }
        try {
            requestHeader.parse();
            HttpMethodHandlerFactory factory = new HttpMethodHandlerFactory(requestHeader);
            HttpMethodHandler handler = factory.getHandler();
            handler.processRequest();
            response = handler.getResponse();
        }
        catch ( RequestMethodNotRecognizedException expected) {
            ServerResponse serverResponse = new ServerMethodNotAllowedResponse();
            serverResponse.setAllowedMethods(new ArrayList<String>(HttpConstant.REQUEST_METHODS.keySet()));
            response = serverResponse;
        }
        catch ( Exception e) {
            ServerResponse serverResponse = new ServerBadRequestResponse();
            response = serverResponse;
        }

    }

    public ServerResponse getResponse() {
        return response;
    }

    private String getVersionedHttpResponse(String responseCode) {
        return HttpConstant.HTTP_VERSION + " " + responseCode;
    }

    public void setRequest(String request) {
        this.requestHeader = new RequestHeader(request);
    }
}
