package com.undecided.responses;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.constants.ServerParamConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.SimpleDateTime;
import com.undecided.utils.SimpleDateTimeInterface;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by silver.lu on 11/11/14.
 */
public abstract class ServerResponse {
    protected HttpResponseCode responseCode;
    protected String serverType;
    protected String contentType;
    protected String charSet;
    protected byte[] responseBody;
    protected SimpleDateTimeInterface dateTime;
    protected List<String> allowedMethods;
    protected String eTag;
    protected String contentMimeType;
    protected String redirectLocation;
    private boolean suppressBody;

    public ServerResponse() {
        this(HttpResponseCode.BadRequest);
    }

    public ServerResponse(HttpResponseCode responseCode) {
        this.responseCode = responseCode;
        this.serverType = "undecided";
        this.charSet = "UTF-8";
        this.contentType = "text/html";
        this.responseBody = "".getBytes();
        this.allowedMethods = null;
        this.suppressBody = false;
    }

    public void setDateTime(SimpleDateTimeInterface dateTime) {
        this.dateTime = dateTime;
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
        if (null == responseBody) {
            return 0;
        }

        return responseBody.length;
    }

    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public abstract String getHeader();

    public byte[] getBody() {
        if ( suppressBody ) {
            return "".getBytes();
        }
        else {
            return responseBody;
        }
    }

    public void setAllowedMethods(List<String> allowedMethods) {}

    public void setETag(String eTag){}

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBodyAsString() {
        return new String(responseBody);
    }

    public void setRedirectLocation(String location) {}

    public void suppressBody() {
        suppressBody = true;
    }
}
