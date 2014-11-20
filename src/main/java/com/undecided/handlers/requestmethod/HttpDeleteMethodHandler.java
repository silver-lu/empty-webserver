package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.enums.HttpResponseCode;
import com.undecided.enums.HttpResponseType;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.utils.FileReader;
import com.undecided.utils.FileSystemWrapper;
import com.undecided.utils.FileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by silver.lu on 11/19/14.
 */
public class HttpDeleteMethodHandler extends HttpHandler {
    FileSystemWrapper fsWrapper = null;

    public HttpDeleteMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() throws IOException {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = getFileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (fsWrapper.getFileInspector().exists()) {
            FileWriter fileWriter = fsWrapper.getFileWriter();
            fileWriter.delete();

            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Ok);
            response = serverResponse;
        }
        else {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.NotFound);
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
