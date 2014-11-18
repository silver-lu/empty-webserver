package com.undecided;

import com.undecided.constants.HttpConstant;
import com.undecided.enums.HttpResponseCode;
import com.undecided.exceptions.MissingRequestHeaderException;
import com.undecided.exceptions.RequestMethodNotRecognizedException;
import com.undecided.handlers.HttpHandler;
import com.undecided.handlers.HttpHandlerFactory;
import com.undecided.handlers.RequestValidationExceptionHandler;
import com.undecided.requests.Request;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.validators.RequestRedirectValidator;
import com.undecided.validators.RequestRestrictedMethodValidator;
import com.undecided.validators.RequestValidatorChain;

import java.util.ArrayList;

/**
 * Created by silver.lu on 11/11/14.
 */
public class RequestHandler {
    Request request;
    ServerResponse response;

    public RequestHandler(String request) {
        this.request = new Request(request);
    }

    public RequestHandler() {

    }

    public void processRequest() throws MissingRequestHeaderException {
        RequestValidatorChain validatorChain = new RequestValidatorChain();
        validatorChain.add(new RequestRedirectValidator(Server.configuration.getRedirectsConfig()));
        validatorChain.add(new RequestRestrictedMethodValidator(Server.configuration.getRestrictedMethodsConfig()));

        try {
            request.parse();
            validatorChain.validateChain(request);

            HttpHandler handler = HttpHandlerFactory.getInstance(request);
            handler.processRequest();
            response = handler.getResponse();
        }
        catch ( RequestMethodNotRecognizedException expected) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.MethodNotAllowed);
            serverResponse.setAllowedMethods(new ArrayList<String>(HttpConstant.REQUEST_METHODS.keySet()));
            response = serverResponse;
        }
        catch ( Exception expected) {
            RequestValidationExceptionHandler validationExceptionHandler = new RequestValidationExceptionHandler(request, expected);
            validationExceptionHandler.processException();
            response = validationExceptionHandler.getResponse();
        }

    }

    public ServerResponse getResponse() {
        return response;
    }

    private String getVersionedHttpResponse(String responseCode) {
        return HttpConstant.HTTP_VERSION + " " + responseCode;
    }

    public void setRequest(String request) {
        this.request = new Request(request);
    }
}
