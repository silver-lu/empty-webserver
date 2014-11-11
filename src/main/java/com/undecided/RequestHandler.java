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
            } else if (request.getRequestUrl().equals("/")) {
                DirectoryLister lister = new DirectoryLister("/");
                response = "HTTP/1.1 200 OK\n" + lister.getStringReadableFilesAndDirectories();
            } else {
                response = "HTTP/1.1 404 Not Found";
            }
        }
        catch ( RequestMethodNotRecognizedException expected) {
            response = "HTTP/1.1 405 Method Not Allowed";
        }
        catch ( Exception e) {
            response = "HTTP/1.1 400 Bad Request";
        }
    }

    public String getResponse() {
        return response;
    }
}
