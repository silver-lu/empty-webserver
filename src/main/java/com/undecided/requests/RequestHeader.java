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
    private String ClientUserAgent;
    private String ClientHost;
    private String ClientAcceptLanguage;
    private String ClientConnection;
    private String ClientContentType;
    private int ClientContentLength;
    private String ClientBody;
    private String ClientInput;
    private Map<HttpSupportedHeader, String> additionalHeaders;

    public RequestHeader(String rawInput) {
        this.rawInput = rawInput;
        additionalHeaders = new HashMap<HttpSupportedHeader, String>();
    }

    public void parse() throws Exception {
        String[] requests = rawInput.split("\n");
        String requestLine = requests[0];

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
                additionalHeaders.put(HttpConstant.SUPPORTED_HEADERS.get(params[0]), params[1]);
            }
        }
    }



    public void parseClientHeaders() throws Exception {
        String[] requests = rawInput.split("\n");

        // remove the first line of the request
        ClientInput = rawInput.substring(requests[0].length(), rawInput.length());

        for (String requestLine : requests) {
            // skip the first header
            if (requestLine.equals(requests[0])) {
                continue;
            }

            parseClientLine(requestLine);

            // We can stop once we get see the client content length
            String[] clientCommand = requestLine.split(": ");
            if (clientCommand[0].equals(HttpConstant.CLIENT_CONTENT_LENGTH)) {
                break;
            }
        }
    }

    private void parseClientLine(String requestLine) {
        String[] clientCommand = requestLine.split(": ");

        if (clientCommand[0].equals(HttpConstant.CLIENT_USER_AGENT)) {
            this.ClientUserAgent = clientCommand[1];
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());
        } else if (clientCommand[0].equals(HttpConstant.CLIENT_HOST)) {
            this.ClientHost = clientCommand[1];
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());
        } else if (clientCommand[0].equals(HttpConstant.CLIENT_ACCEPT_LANGUAGE)) {
            this.ClientAcceptLanguage = clientCommand[1];
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());
        } else if (clientCommand[0].equals(HttpConstant.CLIENT_CONNECTION)) {
            this.ClientConnection = clientCommand[1];
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());
        } else if (clientCommand[0].equals(HttpConstant.CLIENT_CONTENT_TYPE)) {
            this.ClientContentType = clientCommand[1];
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());
        } else if (clientCommand[0].equals(HttpConstant.CLIENT_CONTENT_LENGTH)) {
            this.ClientContentLength = Integer.parseInt(clientCommand[1]);

            // We can assume that the last part after Content-Length is the body:
            ClientInput = ClientInput.substring(requestLine.length() + 1, ClientInput.length());

            ClientBody = ClientInput;
        }
    }

    public String getClientBody() {
        return ClientBody;
    }


    public String getHeaderParam(HttpSupportedHeader header) {
        return additionalHeaders.get(header);
    }
}
