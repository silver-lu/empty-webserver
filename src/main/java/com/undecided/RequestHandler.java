package com.undecided;

import com.undecided.exceptions.RequestMethodNotRecognizedException;

import java.io.File;

/**
 * Created by silver.lu on 11/11/14.
 */
public class RequestHandler {
    RequestHeader requestHeader;
    String response;

    public RequestHandler(String request) {
        this.requestHeader = new RequestHeader(request);
    }

    public void processRequest() {
        try {
            requestHeader.parse();
            RequestMethodRouter methodRouter = new RequestMethodRouter(requestHeader);
            HttpMethodHandler handler = methodRouter.getHandler();
            handler.processRequest();
            response = handler.getResponse();
        }
        catch ( RequestMethodNotRecognizedException expected) {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.MethodNotAllowed);
            response = serverResponse.getHttpResponse();
        }
        catch ( Exception e) {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.BadRequest);
            response = serverResponse.getHttpResponse();
        }
    }

    public String getResponse() {
        return response;
    }

    private String getVersionedHttpResponse(String responseCode) {
        return HttpConstant.HTTP_VERSION + " " + responseCode;
    }
}
