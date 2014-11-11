package com.undecided;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/11/14.
 */
public interface HttpConstant {
    public final static String BAD_REQUEST = "400 Bad Request";
    public final static String NOT_FOUND = "404 Not Found";
    public final static String METHOD_NOT_ALLOWED = "405 Method Not Allowed";
    public final static String OK = "200 OK";

    public final static String HTTP_VERSION = "HTTP/1.1";

    public final static String GET_REQUEST = "GET";
    public final static Map<HttpResponseCode, String> RESPONSE_CODES = new HashMap<HttpResponseCode, String>() {
        {
            put(HttpResponseCode.BadRequest, BAD_REQUEST);
            put(HttpResponseCode.MethodNotAllowed, METHOD_NOT_ALLOWED);
            put(HttpResponseCode.NotFound, NOT_FOUND);
            put(HttpResponseCode.Ok, OK);
        }
    };
}
