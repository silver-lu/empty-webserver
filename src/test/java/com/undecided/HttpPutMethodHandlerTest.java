package com.undecided;

import com.undecided.constants.ServerParamConstant;
import com.undecided.handlers.HttpPutMethodHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by anthony.chai on 11/15/14.
 */
public class HttpPutMethodHandlerTest {
    @Before
    public void setUp() throws Exception {
        Server.startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
    }

    @Test
    public void testPutReturns200ForExistingFile() throws Exception {
        RequestHeader requestHeader = new RequestHeader("PUT /form HTTP/1.1");
        requestHeader.parse();
        HttpPutMethodHandler handler = new HttpPutMethodHandler(requestHeader);
        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 200 OK", lines[0]);
    }

    @Test
    public void testPutReturns201ForNewFile() throws Exception {
        String content = "PUT /form HTTP/1.1\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE5.01; Windows NT)\n" +
                "Host: www.tutorialspoint.com\n" +
                "Accept-Language: en-us\n" +
                "Connection: Keep-Alive\n" +
                "Content-type: text/html\n" +
                "Content-Length: 54\n" +
                "\n" +
                "<html>\n" +
                "<body>\n" +
                "<h1>Hello, World!</h1>\n" +
                "</body>\n" +
                "</html>\n";

        RequestHeader requestHeader = new RequestHeader(content);
        requestHeader.parse();
        HttpPutMethodHandler handler = new HttpPutMethodHandler(requestHeader);
        handler.processRequest();

        String[] lines = handler.getResponse().getHeader().split(System.lineSeparator());
        assertEquals("HTTP/1.1 201 OK", lines[0]);
    }
}
