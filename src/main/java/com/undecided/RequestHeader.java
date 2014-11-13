package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpVersion;
import com.undecided.exceptions.*;

/**
 * Created by silver.lu on 11/10/14.
 */
public class RequestHeader {

    private String rawInput;
    private HttpRequestMethod requestMethod;
    private String requestUrl;
    private HttpVersion httpVersion;

    public RequestHeader(String rawInput) {
        this.rawInput = rawInput;
    }

    public void parse() throws Exception {
        String requestLine = rawInput;
        if ( requestLine == null || requestLine.length() < 10) {
            throw new MalformedRequestException();
        }

        String[] requestParams = requestLine.split(" ");
        if ( requestParams.length != 3 ) {
            throw new MalformedRequestException();
        }
        parseRequestMethod(requestParams[0]);
        parseRequestUrl(requestParams[1]);
        parseHttpVersion(requestParams[2]);
    }

    private void parseHttpVersion(String httpVersion) throws Exception{
        if ( httpVersion.equals(HttpConstant.HTTP_VERSION) ) {
            this.httpVersion = HttpVersion.OneDotOne;
        }
        else {
            throw new ProtocolNotRecognizedException();
        }
    }

    private void parseRequestUrl(String requestUrl) throws Exception{
        if ( (requestUrl.length() > 0 && requestUrl.charAt(0) == '/') || requestUrl.equals("*") ) {
            this.requestUrl = requestUrl;
        }
        else {
            throw new MalformedRequestException();
        }
    }

    private void parseRequestMethod(String requestMethod) throws Exception {
        if ( requestMethod.equals(HttpConstant.GET_REQUEST) ) {
            this.requestMethod = HttpRequestMethod.Get;
        }
        else if ( requestMethod.equals(HttpConstant.OPTIONS_REQUEST) ){
            this.requestMethod = HttpRequestMethod.Options;
        }
        else if ( requestMethod.equals(HttpConstant.HEAD_REQUEST) ) {
            this.requestMethod = HttpRequestMethod.Head;
        }
        else if ( requestMethod.equals(HttpConstant.POST_REQUEST) ) {
            this.requestMethod = HttpRequestMethod.Post;
        }
        else if ( requestMethod.equals(HttpConstant.PUT_REQUEST) ) {
            this.requestMethod = HttpRequestMethod.Put;
        }
        else {
            throw new RequestMethodNotRecognizedException();
        }
    }

    public HttpRequestMethod getRequestMethod() {
        return requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public HttpVersion getHttpVersion() {
        return httpVersion;
    }
}
