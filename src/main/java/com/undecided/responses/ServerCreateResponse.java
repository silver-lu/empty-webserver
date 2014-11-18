package com.undecided.responses;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.SimpleDateTime;

/**
 * Created by anthony.chai on 11/17/14.
 */
public class ServerCreateResponse extends ServerResponse {
    public ServerCreateResponse() {
        super(HttpResponseCode.Created);
    }

    @Override
    public String getHeader() {
        String header = "";
        String responseBody = "<html>\n" +
                "<body>\n" +
                "<h1>The file was created.</h1>\n" +
                "</body>\n" +
                "</html>";

        if (dateTime == null) { dateTime = new SimpleDateTime(); }

        this.setResponseBody(responseBody.getBytes());

        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        header += String.format(HttpResponseConstant.TPL_RESPONSE_TIMESTAMP, dateTime.now());
        header += String.format(HttpResponseConstant.TPL_SERVER_TYPE, serverType);
        header += String.format(HttpResponseConstant.TPL_MIME_TYPE, contentType);
        header += String.format(HttpResponseConstant.TPL_CONTENT_LENGTH, getContentLength());

        return header;
    }
}
