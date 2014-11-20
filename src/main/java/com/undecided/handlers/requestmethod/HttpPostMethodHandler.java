package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.enums.HttpResponseType;
import com.undecided.requests.Request;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.ServerResponse;
import com.undecided.enums.HttpResponseCode;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.responses.ServerStandardResponse;
import com.undecided.utils.DirectoryLister;
import com.undecided.utils.FileReader;
import com.undecided.utils.FileSystemWrapper;
import com.undecided.utils.FileWriter;

import java.io.File;
import java.io.IOException;

/**
 * Created by silver.lu on 11/12/14.
 */
public class HttpPostMethodHandler extends HttpHandler {
    FileSystemWrapper fsWrapper = null;

    public HttpPostMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() throws IOException {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = getFileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        if (! fsWrapper.getFileInspector().exists()) {
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
        else {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.Ok);
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
