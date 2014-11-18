package com.undecided.responses.responsecode;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponse;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerBadRequestResponse extends ServerResponse {
    public ServerBadRequestResponse() {
        super(HttpResponseCode.BadRequest);
    }

    @Override
    public String getHeader() {
        String header = "";
        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        return header;
    }
}
