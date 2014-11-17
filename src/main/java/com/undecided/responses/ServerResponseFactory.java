package com.undecided.responses;

import com.undecided.enums.HttpResponseCode;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerResponseFactory {
    public  static ServerResponse getInstance(HttpResponseCode responseCode) {
        if ( responseCode == HttpResponseCode.NotFound ) {
            return new ServerNotFoundResponse();
        }
        else if (responseCode == HttpResponseCode.BadRequest ) {
            return new ServerBadRequestResponse();
        }
        else if (responseCode == HttpResponseCode.Redirect) {
            return new ServerRedirectResponse();
        }
        else if (responseCode == HttpResponseCode.MethodNotAllowed) {
            return new ServerMethodNotAllowedResponse();
        }
        else if (responseCode == HttpResponseCode.Unauthorized) {
            return new ServerUnauthorizedResponse();
        }
        return null;
    }
}
