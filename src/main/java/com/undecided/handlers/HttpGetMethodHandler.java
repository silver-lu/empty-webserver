package com.undecided.handlers;

import com.undecided.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.*;
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


        if ( requestHeader.getRequestUrl().equals("/redirect") ) {
            ServerResponse serverResponse = new ServerRedirectResponse();
            serverResponse.setRedirectLocation("http://localhost:5000/");
            response = serverResponse;
        }
        else if (requestHeader.getRequestUrl().equals("/logs")) {
            ServerResponse serverResponse = new ServerUnauthorizedResponse();
            String message = "Authentication required";
            serverResponse.setResponseBody(message.getBytes());

            //response = serverResponse.getBasicAuthResponse();
            response = serverResponse;
        }
        else if (! lister.exists()){
            ServerResponse serverResponse = new ServerNotFoundResponse();
            response = serverResponse;
        }
        else if ( lister.isFile()) {
            ServerResponse serverResponse = new ServerGetFileResponse();
            serverResponse.setContentType(lister.getFileMimeType());
            serverResponse.setResponseBody(lister.getFileContent());
            response = serverResponse;
        }
        else if ( lister.isDirectory()) {
            lister.parseDirectory();
            ServerResponse serverResponse = new ServerGetDirectoryResponse();
            serverResponse.setResponseBody(lister.getLinkableDirectory().getBytes());
            response = serverResponse;
        }

    }

}
