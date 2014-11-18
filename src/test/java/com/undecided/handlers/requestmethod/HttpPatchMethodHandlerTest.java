package com.undecided.handlers.requestmethod;

import com.undecided.Server;
import com.undecided.constants.ServerParamConstant;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by silver.lu on 11/18/14.
 */
public class HttpPatchMethodHandlerTest {

    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }

    @Test
    public void testThatETagDirectiveInTheHeaderCanBeRead() throws Exception {
        String rawRequest = "PATCH /file.txt HTTP/1.1" + System.lineSeparator();
        rawRequest += "Host: www.example.com" + System.lineSeparator();
        rawRequest += "Content-Type: application/example" + System.lineSeparator();
        rawRequest += "If-Match: \"e0023aa4e\"" + System.lineSeparator();
        rawRequest += "Content-Length: 20" + System.lineSeparator();
        rawRequest += System.lineSeparator();
        rawRequest += "abcdefghijabcdefghij";

      //  Request =

    }
}
