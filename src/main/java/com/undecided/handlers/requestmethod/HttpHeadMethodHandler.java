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
public class HttpHeadMethodHandler extends HttpGetMethodHandler {
    public HttpHeadMethodHandler(Request request) {
        super(request);
    }

    @Override
    public ServerResponse getResponse() {
        response.suppressBody();
        return response;
    }
}
