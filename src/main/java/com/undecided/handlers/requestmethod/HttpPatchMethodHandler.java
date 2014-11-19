package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.enums.HttpResponseCode;
import com.undecided.enums.HttpResponseType;
import com.undecided.enums.HttpSupportedHeader;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/18/14.
 */
public class HttpPatchMethodHandler extends HttpHandler {

    private DirectoryLister lister = null;

    public HttpPatchMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() throws Exception {
        RequestHeader requestHeader = request.getRequestHeader();
        DirectoryLister lister = getDirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (lister.isFile()) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.PatchFile);
            serverResponse.setETag(requestHeader.getHeaderParam(HttpSupportedHeader.ETag));
            response = serverResponse;
        }
        else {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.PatchFile);
            serverResponse.setETag(requestHeader.getHeaderParam(HttpSupportedHeader.ETag));
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
