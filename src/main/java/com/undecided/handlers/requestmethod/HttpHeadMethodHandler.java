package com.undecided.handlers.requestmethod;

import com.undecided.Request;
import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.enums.HttpResponseType;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpHeadMethodHandler extends HttpHandler {
    public HttpHeadMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        RequestHeader requestHeader = request.getRequestHeader();
        DirectoryLister lister = new DirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if ( requestHeader.getRequestUrl().equals("/redirect") ) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Redirect);
            serverResponse.setRedirectLocation("http://localhost:5000/");
            response = serverResponse;
        }
        else if (requestHeader.getRequestUrl().equals("/logs")) {
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

        response.setResponseBody(null);
    }
}
