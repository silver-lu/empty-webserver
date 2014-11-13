package com.undecided.handlers;

import com.undecided.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpGetMethodHandler extends HttpMethodHandler {

    public HttpGetMethodHandler(RequestHeader requestHandler) {
        super(requestHandler);
    }

    @Override
    public void processRequest() {
        if (requestHeader.getRequestUrl().equals("/ping")) {
            response = "Pong";
        } else if (requestHeader.getRequestUrl().equals("/")) {
            DirectoryLister lister = new DirectoryLister(new File(Server.startDirectory));
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
            serverResponse.setResponseBody(lister.getStringReadableFilesAndDirectories());
            response = serverResponse.getHttpResponse();
        } else {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.NotFound);
            response = serverResponse.getHttpResponse();
        }
    }

}
