package com.undecided;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponse {
    private HttpResponseCode responseCode;
    private String serverType;
    private String contentType;
    private String charSet;

    public ServerResponse() {
        this(HttpResponseCode.BadRequest);
    }

    public ServerResponse(HttpResponseCode responseCode) {
        this.responseCode = responseCode;
        this.serverType = "undecided";
        this.charSet = "UTF-8";
        this.contentType = "text/html";
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
}
