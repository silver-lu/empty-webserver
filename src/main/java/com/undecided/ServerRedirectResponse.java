package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.constants.HttpResponseConstant;
import com.undecided.enums.HttpResponseCode;

/**
 * Created by silver.lu on 11/13/14.
 */
public class ServerRedirectResponse extends ServerResponse {
    public ServerRedirectResponse(HttpResponseCode responseCode) {
        super(responseCode);
    }

    public String getHeader() {
        String header = "";
        header += String.format(HttpResponseConstant.TPL_RESPONSE_CODE, HttpConstant.HTTP_VERSION, HttpConstant.RESPONSE_CODES.get(responseCode));
        header += String.format(HttpResponseConstant.TPL_NEW_LOCATION, this.redirectLocation);
        return header;
    }

    public void setRedirectLocation(String redirectLocation) {
        this.redirectLocation = redirectLocation;
    }
}
