package com.undecided.handlers.requestmethod;

import com.undecided.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.enums.HttpResponseType;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.*;
import com.undecided.utils.DirectoryLister;
import com.undecided.utils.FileReader;
import com.undecided.utils.FileSystemWrapper;

import java.io.File;
import java.util.Base64;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpGetMethodHandler extends HttpHandler {

    private FileSystemWrapper fsWrapper = null;

    public HttpGetMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = new FileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (requestHeader.getRequestUrl().equals("/logs")) {
            String authString = requestHeader.getAuthorization();

            String userPass = "admin:hunter2";
            Base64.Encoder encoder = Base64.getEncoder();

            String match = "Basic " + encoder.encodeToString(userPass.getBytes());

            ServerResponse serverResponse;
            if (match.equals(authString)) {
                serverResponse = ServerResponseFactory.getInstance(HttpResponseType.GetFile);
                serverResponse.setContentType(fsWrapper.getFileInspector().getFileMimeType());
                FileReader fileReader = fsWrapper.getFileReader();
                fileReader.read();
                serverResponse.setResponseBody(fileReader.getContent());
            } else {
                serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Unauthorized);
                String message = "Authentication required";
                serverResponse.setResponseBody(message.getBytes());
                //response = serverResponse.getBasicAuthResponse();
            }

            response = serverResponse;
        }
        else if (! fsWrapper.getFileInspector().exists()){
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.NotFound);
            response = serverResponse;
        }
        else if ( fsWrapper.getFileInspector().isFile()) {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.GetFile);
            serverResponse.setContentType(fsWrapper.getFileInspector().getFileMimeType());
            FileReader fileReader = fsWrapper.getFileReader();
            fileReader.read();
            serverResponse.setResponseBody(fileReader.getContent());
            response = serverResponse;
        }
        else if ( fsWrapper.getFileInspector().isDirectory()) {
            DirectoryLister lister = fsWrapper.getDirectoryLister();
            lister.parseDirectory();
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.GetDirectory);
            serverResponse.setResponseBody(lister.getLinkableDirectory().getBytes());
            response = serverResponse;
        }

    }

    private FileSystemWrapper getFileSystemWrapper(File baseDirectory) {
        if ( fsWrapper != null ) {
            return fsWrapper;
        }
        else {
            return new FileSystemWrapper(baseDirectory);
        }
    }

    public void setFileSystemWrapper(FileSystemWrapper fsWrapper) {
        this.fsWrapper = fsWrapper;
    }

}
