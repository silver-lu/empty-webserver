package com.undecided.responses.responsecode;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.constants.ServerParamConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;
import com.undecided.utils.SimpleDateTime;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerUnauthorizedResponse extends ServerResponse {
    public ServerUnauthorizedResponse() {super(HttpResponseCode.Unauthorized);}

    @Override
    public String getHeader() {
        String header = "";

        if (dateTime == null) { dateTime = new SimpleDateTime(); }

        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        header += String.format(HttpResponseConstant.TPL_RESPONSE_TIMESTAMP, dateTime.now());
        header += String.format(HttpResponseConstant.TPL_SERVER_TYPE, serverType);
        header += String.format(HttpResponseConstant.TPL_BASIC_AUTH, ServerParamConstant.BASIC_REALM);
        header += String.format(HttpResponseConstant.TPL_CONTENT_TYPE, contentType, charSet);
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());

        return header;
    }
}
