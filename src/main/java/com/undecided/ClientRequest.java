package com.undecided;

import java.io.BufferedReader;
import java.io.Reader;

/**
 * Created by silver.lu on 11/10/14.
 */
public class ClientRequest {

    private BufferedReader rawInput;
    private HttpRequestMethod requestMethod;
    private String requestUrl;
    private HttpVersion httpVersion;

    public ClientRequest(Reader rawInput) throws Exception {
        this.rawInput = new BufferedReader(rawInput);
        parse();
    }

    private void parse() throws Exception {
        String requestLine = rawInput.readLine();
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
        if ( httpVersion.equals("HTTP/1.1") ) {
            this.httpVersion = HttpVersion.OneDotOne;
        }
        else {
            throw new ProtocolNotRecognizedException();
        }
    }

    private void parseRequestUrl(String requestUrl) throws Exception{
        if ( requestUrl.length() > 0 && requestUrl.charAt(0) == '/' ) {
            this.requestUrl = requestUrl;
        }
        else {
            throw new MalformedRequestException();
        }
    }

    private void parseRequestMethod(String requestMethod) throws Exception {
        if ( requestMethod.equals("GET") ) {
            this.requestMethod = HttpRequestMethod.Get;
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
