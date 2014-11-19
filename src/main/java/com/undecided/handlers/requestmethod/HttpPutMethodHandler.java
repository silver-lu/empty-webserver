package com.undecided.handlers.requestmethod;

import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.Server;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.DirectoryLister;
import com.undecided.utils.FileSystemWrapper;

import java.io.File;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPutMethodHandler extends HttpHandler {
    FileSystemWrapper fsWrapper = null;

    public HttpPutMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = getFileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        try {
            requestHeader.parseClientHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response = new ServerStandardResponse(HttpResponseCode.Ok);
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
