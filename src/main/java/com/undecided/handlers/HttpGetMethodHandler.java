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
        DirectoryLister lister = new DirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (requestHeader.getRequestUrl().equals("/logs")) {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.AuthorizedRequired);
            serverResponse.setResponseBody("Authentication required");
            response = serverResponse.getBasicAuthResponse();
        }
        else if (! lister.exists()){
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.NotFound);
            response = serverResponse.getHttpResponse();
        }
        else if ( lister.isFile()) {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
            serverResponse.setResponseBody(lister.getFileContent());
            response = serverResponse.getHttpResponse();
        }
        else if ( lister.isDirectory()) {
            lister.parseDirectory();
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
            serverResponse.setResponseBody(lister.getStringReadableFilesAndDirectories());
            response = serverResponse.getHttpResponse();
        }
/*
        if (requestHeader.getRequestUrl().equals("/ping")) {
            response = "Pong";
        } else if (requestHeader.getRequestUrl().equals("/")) {
            lister.parseDirectory();
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.Ok);
            serverResponse.setResponseBody(lister.getStringReadableFilesAndDirectories());
            response = serverResponse.getHttpResponse();
        } else {
            ServerResponse serverResponse = new ServerResponse(HttpResponseCode.NotFound);
            response = serverResponse.getHttpResponse();
        }*/
    }

}
