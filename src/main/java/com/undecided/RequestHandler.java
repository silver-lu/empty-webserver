package com.undecided;

/**
 * Created by silver.lu on 11/11/14.
 */
public class RequestHandler {
    ClientRequest request;
    String response;

    public RequestHandler(String request) {
        this.request = new ClientRequest(request);
    }

    public void processRequest() {
        try {
            request.parse();
            if (request.getRequestUrl().equals("/ping")) {
                response = "Pong";
            } else {
                response = getVersionedHttpResponse(HttpResponseCodes.NOT_FOUND);
            }
        }
        catch ( RequestMethodNotRecognizedException expected) {
            response = getVersionedHttpResponse(HttpResponseCodes.METHOD_NOT_ALLOWED);
        }
        catch ( Exception e) {
            response = getVersionedHttpResponse(HttpResponseCodes.BAD_REQUEST);
        }
    }

    public String getResponse() {
        return response;
    }

    private String getVersionedHttpResponse(String responseCode) {
        return HttpResponseCodes.HTTP_VERSION + " " + responseCode;
    }
}
