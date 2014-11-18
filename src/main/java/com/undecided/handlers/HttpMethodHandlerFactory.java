package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.Server;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.utils.DirectoryLister;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpMethodHandlerFactory {
    private final RequestHeader requestHeader;

    public HttpMethodHandlerFactory(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public HttpMethodHandler getHandler() {

        if ( this.requestHeader.getRequestMethod() == HttpRequestMethod.Get) {
            return new HttpGetMethodHandler(requestHeader);
        }
        else if ( this.requestHeader.getRequestMethod() == HttpRequestMethod.Options) {
            return new HttpOptionsMethodHandler(requestHeader);
        }
        else if ( this.requestHeader.getRequestMethod() == HttpRequestMethod.Put) {
            HttpPutMethodHandler handler = new HttpPutMethodHandler(requestHeader);

            String fileName = Server.startDirectory + requestHeader.getRequestUrl();
            DirectoryLister lister = new DirectoryLister(new File(fileName));
            handler.setDirectoryLister(lister);

            return handler;
        }
        else if ( this.requestHeader.getRequestMethod() == HttpRequestMethod.Post) {
            return new HttpPostMethodHandler(requestHeader);
        }
        else if ( this.requestHeader.getRequestMethod() == HttpRequestMethod.Head) {
            return new HttpHeadMethodHandler(requestHeader);
        }
        return null;
    }
}
