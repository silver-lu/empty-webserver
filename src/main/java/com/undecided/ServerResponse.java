package com.undecided;

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
public class ServerResponse {
    protected HttpResponseCode responseCode;
    protected String serverType;
    protected String contentType;
    protected String charSet;
    protected byte[] responseBody;
    protected SimpleDateTimeInterface dateTime;
    protected List<String> allowedMethods;
    protected String contentMimeType;

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
    }

    public ServerResponse(SimpleDateTimeInterface dateTimeInterface) {
        this(HttpResponseCode.BadRequest);
        dateTime = dateTimeInterface;
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

        return responseBody.length;
    }

    public void setResponseBody(byte[] responseBody) {
        this.responseBody = responseBody;
    }

    public String getHeader() {
        String header = "";

        header += getBasicHeader();
        header += getContentHeader();

        return header;

    }

    public String getBasicAuthHeader() {
        String header = "";

        header += getBasicHeader();
        header += String.format(HttpResponseConstant.TPL_BASIC_AUTH, ServerParamConstant.BASIC_REALM);
        header += getContentHeader();

        return header;
    }

    private String getBasicHeader() {
        String header = "";

        if (dateTime == null) { dateTime = new SimpleDateTime(); }

        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        header += String.format(HttpResponseConstant.TPL_RESPONSE_TIMESTAMP, dateTime.now());
        header += String.format(HttpResponseConstant.TPL_SERVER_TYPE, serverType);
        if (allowedMethods != null) {
            header += String.format(HttpResponseConstant.TPL_ALLOWED_METHODS, String.join(",", allowedMethods));
        }

        return header;
    }

    private String getContentHeader() {
        String header = "";

        if (contentType.equals("image/jpeg")) {
            header += "Content-Type: image/jpeg" + System.lineSeparator();
        }
        else {
            header += String.format(HttpResponseConstant.TPL_CONTENT_TYPE, contentType, charSet);
        }
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());

        return header;
    }

    public byte[] getBody() {
        return responseBody;
    }

//    public String getHttpResponse() {
//        return getHeader() + System.lineSeparator() + getBody();
//    }
//
//    public String getBasicAuthResponse() {
//        return getBasicAuthHeader() + System.lineSeparator() + getBody();
//    }

    public void setAllowedMethods(List<String> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBodyAsString() {
        return new String(responseBody);
    }
}
