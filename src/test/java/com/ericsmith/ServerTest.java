package com.ericsmith;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

public class ServerTest {

    Thread serverThread;

    @Test
    public void testServerCanBeInitializedWithPortNumber() throws Exception {
        Server server = new Server(5000);
        assertEquals(5000, server.getPort());
    }

    @Test
    public void testServerCanBeStarted() throws Exception {
        startPrivateServer(5000);
        Socket serverSocket = new Socket("localhost", 5000);
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        assertEquals("Server Up", in.readLine());
    }

    private void startPrivateServer(final int portNumber) {
        serverThread = new Thread() {
            public void run() {
                Server server = new Server(portNumber);
                try {
                    server.run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        serverThread.start();
    }
}
