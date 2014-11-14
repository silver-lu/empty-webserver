package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.MissingRequestHeaderException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import com.undecided.handlers.HttpMethodHandler;
import com.undecided.handlers.HttpMethodHandlerFactory;

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
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.MethodNotAllowed);
            response = serverResponse;
        }
        catch ( Exception e) {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.BadRequest);
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
