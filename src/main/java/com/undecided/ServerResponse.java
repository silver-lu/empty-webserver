package com.undecided;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponse {
    private HttpResponseCode responseCode;
    private String serverType;
    private String contentType;
    private String charSet;
    private String responseBody;

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
        header += String.format(HttpResponseConstant.TPL_CONTENT_TYPE, contentType, charSet);
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());
        return header;

    }

    public String getResponseBody() {
        return responseBody;
    }
}
