package com.undecided.handlers;

import com.sun.xml.internal.bind.v2.TODO;
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
    public void testTheContentOfThePatchRequestCanBeRead() throws Exception {

    }
}
