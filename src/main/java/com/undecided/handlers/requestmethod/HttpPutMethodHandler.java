package com.undecided.handlers.requestmethod;

import com.undecided.enums.HttpResponseType;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.Server;
import com.undecided.handlers.HttpHandler;
import com.undecided.responses.*;
import com.undecided.enums.HttpResponseCode;
import com.undecided.utils.DirectoryLister;
import com.undecided.utils.FileReader;
import com.undecided.utils.FileSystemWrapper;
import com.undecided.utils.FileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPutMethodHandler extends HttpHandler {
    FileSystemWrapper fsWrapper = null;

    public HttpPutMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() throws IOException {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = getFileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));
/*
        try {
            requestHeader.parseClientHeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        FileWriter fileWriter = fsWrapper.getFileWriter();
        fileWriter.setContent(request.getRequestBody().getContent());
        fileWriter.write();

        ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.GetFile);
        serverResponse.setContentType(fsWrapper.getFileInspector().getFileMimeType());
        FileReader fileReader = fsWrapper.getFileReader();
        fileReader.read();
        serverResponse.setResponseBody(fileReader.getContent());
        response = serverResponse;
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
