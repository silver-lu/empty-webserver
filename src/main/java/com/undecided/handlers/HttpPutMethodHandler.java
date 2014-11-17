package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.responses.*;
import com.undecided.Server;
import com.undecided.ServerRedirectResponse;
import com.undecided.ServerResponse;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.MalformedRequestException;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPutMethodHandler extends HttpMethodHandler {
    public HttpPutMethodHandler(RequestHeader requestHeader) {
        super(requestHeader);
    }

    @Override
    public void processRequest() {
        ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Ok);
        response = serverResponse;
        
        // parse client command header
        try {
            requestHeader.parseClientHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }

        requestHeader.getClientBody();

        DirectoryLister lister = new DirectoryLister(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (lister.exists()) {
            ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Created);
            String body = "<html>\n" +
                    "<body>\n" +
                    "<h1>The file was created.</h1>\n" +
                    "</body>\n" +
                    "</html>\n";

            serverResponse.setResponseBody(body.getBytes());
            response = serverResponse;
        }
        else {
            ServerResponse serverResponse = new ServerStandardResponse(HttpResponseCode.Ok);

            response = serverResponse;
        }
    }

}
