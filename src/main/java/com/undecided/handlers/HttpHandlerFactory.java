package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.handlers.requestmethod.*;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpHandlerFactory {
    public static HttpHandler getInstance(RequestHeader requestHeader) {
        if ( requestHeader.getRequestMethod() == HttpRequestMethod.Get) {
            return new HttpGetMethodHandler(requestHeader);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Options) {
            return new HttpOptionsMethodHandler(requestHeader);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Put) {
            return new HttpPutMethodHandler(requestHeader);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Post) {
            return new HttpPostMethodHandler(requestHeader);
        }
        else if ( requestHeader.getRequestMethod() == HttpRequestMethod.Head) {
            return new HttpHeadMethodHandler(requestHeader);
        }
        return null;
    }
}
