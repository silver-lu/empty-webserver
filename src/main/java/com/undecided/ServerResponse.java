package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.SimpleDateTime;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponse {
    private HttpResponseCode responseCode;
    private String serverType;
    private String contentType;
    private String charSet;
    private String responseBody;
    private List<String> allowedMethods;

    public ServerResponse() {
        this(HttpResponseCode.BadRequest);
    }

    public ServerResponse(HttpResponseCode responseCode) {
        this.responseCode = responseCode;
        this.serverType = "undecided";
        this.charSet = "UTF-8";
        this.contentType = "text/html";
        this.responseBody = "";
    }

    public HttpResponseCode getResponseCode() {
        return responseCode;
    }

    public String getServerType() {
        return serverType;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCharSet() {
        return charSet;
    }

    public int getContentLength() {
        int contentLength = 0;

        try {
            contentLength = responseBody.getBytes(charSet).length;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        finally {
            return contentLength;
        }
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseHeader() {
        String header = "";
        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        String.format(header += String.format(HttpResponseConstant.TPL_RESPONSE_TIMESTAMP, SimpleDateTime.now()));
        header += String.format(HttpResponseConstant.TPL_SERVER_TYPE, serverType);
        if (allowedMethods != null) {
            header += String.format(HttpResponseConstant.TPL_ALLOWED_METHODS, String.join(",", allowedMethods));
        }
        header += String.format(HttpResponseConstant.TPL_CONTENT_TYPE, contentType, charSet);
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());
        return header;

    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getHttpResponse() {
        return getResponseHeader() + "\r\n" + getResponseBody();
    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }
}
