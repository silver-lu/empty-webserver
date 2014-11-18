package com.undecided;

/**
 * Created by silver.lu on 11/18/14.
 */
public class Request {
    private String rawInput;
    private RequestHeader requestHeader;
    private RequestBody requestBody;
    private String rawHeader;
    private String rawBody;

    public Request(String rawInput) {
        this.rawInput = rawInput;
    }

    public void parse() throws Exception {
        String headAndBodySeperator = System.lineSeparator() + System.lineSeparator();
        if ( rawInput.indexOf(headAndBodySeperator) != -1 ) {
            rawHeader = rawInput.substring(0, rawInput.indexOf(headAndBodySeperator));
            rawBody = rawInput.substring(rawInput.indexOf(headAndBodySeperator) + headAndBodySeperator.length());
        }
        else {
            rawHeader = rawInput;
            rawBody = "";
        }

        requestHeader = new RequestHeader(rawHeader);
        requestHeader.parse();

        requestBody = new RequestBody(rawBody);
        requestBody.parse();
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public RequestBody getRequestBody() {
        return requestBody;
    }
}
