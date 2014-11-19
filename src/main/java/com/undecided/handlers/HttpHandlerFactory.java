package com.undecided.handlers;

import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.handlers.requestmethod.*;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpHandlerFactory {
    public static HttpHandler getInstance(Request request) {
        RequestHeader requestHeader = request.getRequestHeader();

        if ( requestHeader.getRequestMethod() == HttpRequestMethod.Get) {
            return new HttpGetMethodHandler(request);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Options) {
            return new HttpOptionsMethodHandler(request);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Put) {
            return new HttpPutMethodHandler(request);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Post) {
            return new HttpPostMethodHandler(request);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Head) {
            return new HttpHeadMethodHandler(request);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Patch) {
            return new HttpPatchMethodHandler(request);
        }
        return null;
    }
}
