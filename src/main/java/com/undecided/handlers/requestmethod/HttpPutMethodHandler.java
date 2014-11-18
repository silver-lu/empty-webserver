package com.undecided.handlers.requestmethod;

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
    DirectoryLister lister;

    public HttpPutMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        // parse client command header


        String fileName = Server.startDirectory + requestHeader.getRequestUrl();
        DirectoryLister lister = new DirectoryLister(new File(fileName));
        setDirectoryLister(lister);

        try {
            requestHeader.parseClientHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        if (!lister.exists()) {
            lister.saveFile( requestHeader.getClientBody() );

            serverResponse = new ServerCreateResponse();

            response = serverResponse;
        }
        else {*/
            // for now, let's just return 200, because the cob_spec doesn't require 201 (Created)
            response = new ServerStandardResponse(HttpResponseCode.Ok);
        //}
    }

    public void setDirectoryLister(DirectoryLister lister) {
        this.lister = lister;
    }

}
