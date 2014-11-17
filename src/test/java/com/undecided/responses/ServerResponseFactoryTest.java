package com.undecided.responses;

import com.undecided.RequestHeader;
import com.undecided.enums.HttpResponseCode;
import com.undecided.handlers.HttpGetMethodHandler;
import com.undecided.handlers.HttpMethodHandlerFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/17/14.
 */
public class ServerResponseFactoryTest {

    @Test
    public void test404ResponseAreRoutedToServerNotFoundResponse() throws Exception {
        assertEquals(ServerNotFoundResponse.class, ServerResponseFactory.getInstance(HttpResponseCode.NotFound).getClass());
    }

    @Test
    public void test302ResponseAreRoutedToServerRedirectResponse() throws Exception {
        assertEquals(ServerRedirectResponse.class, ServerResponseFactory.getInstance(HttpResponseCode.Redirect).getClass());
    }

    @Test
    public void test400ResponseAreRoutedToServerBadRequestResponse() throws Exception {
        assertEquals(ServerBadRequestResponse.class, ServerResponseFactory.getInstance(HttpResponseCode.BadRequest).getClass());
    }

    @Test
    public void test405ResponseAreRoutedToServerMethodNotAllowedResponse() throws Exception {
        assertEquals(ServerMethodNotAllowedResponse.class, ServerResponseFactory.getInstance(HttpResponseCode.MethodNotAllowed).getClass());
    }

    @Test
    public void test401ResponseAreRoutedToServerUnauthorizedResponse() throws Exception {
        assertEquals(ServerUnauthorizedResponse.class, ServerResponseFactory.getInstance(HttpResponseCode.Unauthorized).getClass());
    }
}
