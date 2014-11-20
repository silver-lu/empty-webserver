package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.enums.HttpResponseCode;
import com.undecided.enums.HttpResponseType;
import com.undecided.enums.HttpSupportedHeader;
import com.undecided.handlers.HttpHandler;
import com.undecided.requests.Request;
import com.undecided.requests.RequestHeader;
import com.undecided.responses.ServerResponse;
import com.undecided.responses.ServerResponseFactory;
import com.undecided.utils.DirectoryLister;
import com.undecided.utils.FileReader;
import com.undecided.utils.FileSystemWrapper;
import com.undecided.utils.FileWriter;

import java.io.File;

/**
 * Created by silver.lu on 11/18/14.
 */
public class HttpPatchMethodHandler extends HttpHandler {

    private FileSystemWrapper fsWrapper = null;

    public HttpPatchMethodHandler(Request request) {
        super(request);
    }

    @Override
    public void processRequest() throws Exception {
        RequestHeader requestHeader = request.getRequestHeader();
        FileSystemWrapper fsWrapper = getFileSystemWrapper(new File(Server.startDirectory + requestHeader.getRequestUrl()));

        FileReader fileReader = fsWrapper.getFileReader();
        fileReader.read();
        if (! fsWrapper.getFileInspector().exists()){
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.NotFound);
            response = serverResponse;
        }
        else if ( fileReader.getCheckSum().equals(request.getRequestHeader().getHeaderParam(HttpSupportedHeader.ETag))) {
            FileWriter fileWriter = fsWrapper.getFileWriter();
            fileWriter.setContent(request.getRequestBody().getContent());
            fileWriter.write();

            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseType.PatchFile);
            serverResponse.setETag(fileWriter.getCheckSum());
            response = serverResponse;
        }
        else {
            ServerResponse serverResponse = ServerResponseFactory.getInstance(HttpResponseCode.PreconditionFailed);
            serverResponse.setETag(fileReader.getCheckSum());
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
