package com.undecided.handlers.requestmethod;

import com.undecided.Request;
import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPutMethodHandler extends HttpHandler {
    DirectoryLister lister = null;

    public HttpPutMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        RequestHeader requestHeader = request.getRequestHeader();
        DirectoryLister lister = getDirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        try {
            requestHeader.parseClientHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response = new ServerStandardResponse(HttpResponseCode.Ok);
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
