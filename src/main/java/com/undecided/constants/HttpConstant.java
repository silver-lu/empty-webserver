package com.undecided.constants;

import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpResponseCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/11/14.
 */
public interface HttpConstant {
    public final static String BAD_REQUEST = "400 Bad Request";
    public final static String AUTHORIZED_REQUIRED = "401 Authentication required";
    public final static String NOT_FOUND = "404 Not Found";
    public final static String METHOD_NOT_ALLOWED = "405 Method Not Allowed";
    public final static String OK = "200 OK";
    public final static String CREATED = "201 OK";
    public final static String REDIRECT = "302 Redirect";

    public final static String HTTP_VERSION = "HTTP/1.1";

    public final static String GET_REQUEST = "GET";
    public final static String OPTIONS_REQUEST = "OPTIONS";
    public final static String POST_REQUEST = "POST";
    public final static String PUT_REQUEST = "PUT";
    public final static String HEAD_REQUEST = "HEAD";

    public final static String CLIENT_USER_AGENT = "User-Agent";
    public final static String CLIENT_HOST = "Host";
    public final static String CLIENT_ACCEPT_LANGUAGE = "Accept-Language";
    public final static String CLIENT_CONNECTION = "Connection";
    public final static String CLIENT_CONTENT_TYPE = "Content-type";
    public final static String CLIENT_CONTENT_LENGTH = "Content-Length";

    public final static Map<String, HttpRequestMethod> REQUEST_METHODS = new HashMap<String, HttpRequestMethod>() {
        {
            put(GET_REQUEST, HttpRequestMethod.Get);
            put(OPTIONS_REQUEST, HttpRequestMethod.Options);
            put(POST_REQUEST, HttpRequestMethod.Post);
            put(PUT_REQUEST, HttpRequestMethod.Put);
            put(HEAD_REQUEST, HttpRequestMethod.Head);
        }
    };

    public final static Map<HttpResponseCode, String> RESPONSE_CODES = new HashMap<HttpResponseCode, String>() {
        {
            put(HttpResponseCode.BadRequest, BAD_REQUEST);
            put(HttpResponseCode.AuthorizedRequired, AUTHORIZED_REQUIRED);
            put(HttpResponseCode.MethodNotAllowed, METHOD_NOT_ALLOWED);
            put(HttpResponseCode.NotFound, NOT_FOUND);
            put(HttpResponseCode.Ok, OK);
            put(HttpResponseCode.Redirect, REDIRECT);
            put(HttpResponseCode.Created, CREATED);
        }
    };
}
