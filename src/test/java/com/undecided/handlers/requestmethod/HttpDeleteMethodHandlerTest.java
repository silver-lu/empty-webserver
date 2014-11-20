package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import com.undecided.mocks.MockFile;
import com.undecided.mocks.MockFileReader;
import com.undecided.mocks.MockFileSystemWrapper;
import com.undecided.mocks.MockFileWriter;
import com.undecided.requests.Request;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/19/14.
 */
public class HttpDeleteMethodHandlerTest {
    private MockFileSystemWrapper fsWrapper;
    private MockFile file;

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;

        file = new MockFile("virtual-path.txt");
        fsWrapper = new MockFileSystemWrapper(file);
        MockFileWriter fileWriter = new MockFileWriter(file);
        fsWrapper.setFileWriter(fileWriter);
        MockFileReader fileReader = new MockFileReader(file);
        fsWrapper.setFileReader(fileReader);
    }

    @Test
    public void testDeletingAFileThatExistReturnA200ResponseCode() throws Exception {
        Request request = new Request("DELETE /virtual-path.txt HTTP/1.1");
        request.parse();

        HttpDeleteMethodHandler handler = new HttpDeleteMethodHandler(request);
        file.setExists(true);
        handler.setFileSystemWrapper(fsWrapper);
        handler.processRequest();
        assertEquals("HTTP/1.1 200 OK", handler.getResponse().getHeader().split(System.lineSeparator())[0]);
        assertTrue(file.isDeleted());
    }
}
