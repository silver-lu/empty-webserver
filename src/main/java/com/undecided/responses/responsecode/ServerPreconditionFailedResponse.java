package com.undecided.responses.responsecode;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import com.undecided.utils.SimpleDateTime;

/**
 * Created by silver.lu on 11/19/14.
 */
public class ServerPreconditionFailedResponse extends ServerResponse {
    public ServerPreconditionFailedResponse() {
        super(HttpResponseCode.PreconditionFailed);
    }

    @Override
    public String getHeader() {
        String header = "";

        if (dateTime == null) { dateTime = new SimpleDateTime(); }

        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        header += String.format(HttpResponseConstant.TPL_RESPONSE_TIMESTAMP, dateTime.now());
        header += String.format(HttpResponseConstant.TPL_SERVER_TYPE, serverType);
        header += String.format(HttpResponseConstant.TPL_ETAG, eTag);
        header += String.format(HttpResponseConstant.TPL_CONTENT_TYPE, contentType, charSet);
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());

        return header;
    }

    @Override
    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
