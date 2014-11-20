package com.undecided.requests;

import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpRequestMethod;
import com.undecided.enums.HttpSupportedHeader;
import com.undecided.enums.HttpVersion;
import com.undecided.exceptions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/10/14.
 */
public class RequestHeader {

    private String rawInput;
    private HttpRequestMethod requestMethod;
    private String requestUrl;
    private HttpVersion httpVersion;
    private Map<HttpSupportedHeader, String> additionalHeaders;

    public RequestHeader(String rawInput) {
        this.rawInput = rawInput;
        additionalHeaders = new HashMap<HttpSupportedHeader, String>();
    }

    public void parse() throws Exception {
        String[] requests = rawInput.split(System.lineSeparator());
        String requestLine = requests[0].trim();

        if (requestLine == null || requestLine.length() < 10) {
            throw new MalformedRequestException();
        }

        String[] requestParams = requestLine.split(" ");

        if (requestParams.length != 3) {
            throw new MalformedRequestException();
        }

        parseRequestMethod(requestParams[0]);
        parseRequestUrl(requestParams[1]);
        parseHttpVersion(requestParams[2]);

        parseAdditionalHeaders(Arrays.copyOfRange(requests, 1, requests.length));
    }

    private void parseHttpVersion(String httpVersion) throws Exception {
        if (httpVersion.equals(HttpConstant.HTTP_VERSION)) {
            this.httpVersion = HttpVersion.OneDotOne;
        } else {
            throw new ProtocolNotRecognizedException();
        }
    }

    private void parseRequestUrl(String requestUrl) throws Exception {
        if ((requestUrl.length() > 0 && requestUrl.charAt(0) == '/') || requestUrl.equals("*")) {
            this.requestUrl = requestUrl;
        } else {
            throw new MalformedRequestException();
        }
    }

    private void parseRequestMethod(String requestMethod) throws Exception {
        HttpRequestMethod method = HttpConstant.REQUEST_METHODS.get(requestMethod);
        if (method == null) {
            throw new RequestMethodNotRecognizedException();
        }
        this.requestMethod = method;
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

    private void parseAdditionalHeaders(String[] headers) {
        for (String header : headers) {
            String[] params = header.split(": ");
            if (HttpConstant.SUPPORTED_HEADERS.containsKey(params[0])) {
                additionalHeaders.put(HttpConstant.SUPPORTED_HEADERS.get(params[0]), params[1].trim());
            }
        }
    }

    public boolean hasHeaderParam(HttpSupportedHeader header) {
        return additionalHeaders.containsKey(header);
    }

    public String getHeaderParam(HttpSupportedHeader header) {
        return additionalHeaders.get(header);
    }

    public String getAuthorization() {
        String auth = additionalHeaders.get(HttpSupportedHeader.Authorization);
        return auth;
    }
}
