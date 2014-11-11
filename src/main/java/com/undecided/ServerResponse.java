package com.undecided;

import java.io.UnsupportedEncodingException;

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

    public int getContentLength() throws UnsupportedEncodingException {
        return responseBody.getBytes(charSet).length;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseHeader() {
        String header =
                HttpConstant.HTTP_VERSION + " " + HttpConstant.RESPONSE_CODES.get(responseCode);
        header += "test";
        return header;
    }

    public String getResponseBody() {
        return "";
    }
}
