package com.undecided.handlers.requestmethod;

import com.undecided.requests.Request;
import com.undecided.responses.*;

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
