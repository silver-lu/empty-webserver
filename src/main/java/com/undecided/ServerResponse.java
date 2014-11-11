package com.undecided;

/**
 * Created by silver.lu on 11/11/14.
 */
public class ServerResponse {
    private HttpResponseCode responseCode;

    public ServerResponse() {
        this(HttpResponseCode.BadRequest);
    }

    public ServerResponse(HttpResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    public HttpResponseCode getResponseCode() {
        return responseCode;
    }
}
