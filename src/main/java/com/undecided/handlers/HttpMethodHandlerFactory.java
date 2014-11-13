package com.undecided.handlers;

import com.undecided.RequestHeader;
import com.undecided.enums.HttpRequestMethod;

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
        return null;
    }
}