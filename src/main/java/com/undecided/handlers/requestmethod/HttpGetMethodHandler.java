package com.undecided.handlers.requestmethod;

import com.undecided.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.enums.HttpResponseType;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.*;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpGetMethodHandler extends HttpHandler {

    private DirectoryLister lister = null;

    public HttpGetMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        RequestHeader requestHeader = request.getRequestHeader();
        DirectoryLister lister = getDirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (requestHeader.getRequestUrl().equals("/logs")) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Unauthorized);
            String message = "Authentication required";
            serverResponse.setResponseBody(message.getBytes());

            //response = serverResponse.getBasicAuthResponse();
            response = serverResponse;
        }
        else if (! lister.exists()){
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.NotFound);
            response = serverResponse;
        }
        else if ( lister.isFile()) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.File);
            serverResponse.setContentType(lister.getFileMimeType());
            serverResponse.setResponseBody(lister.getFileContent());
            response = serverResponse;
        }
        else if ( lister.isDirectory()) {
            lister.parseDirectory();
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.Directory);
            serverResponse.setResponseBody(lister.getLinkableDirectory().getBytes());
            response = serverResponse;
        }

    }

    private DirectoryLister getDirectoryLister(File baseDirectory) {
        if ( lister != null ) {
            return lister;
        }
        else {
            return new DirectoryLister(baseDirectory);
        }
    }

    public void setDirectoryLister(DirectoryLister lister) {
        this.lister = lister;
    }

}
