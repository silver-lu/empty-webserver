package com.undecided;

import org.junit.After;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by silver.lu on 11/10/14.
 */
public class MessageBusTest {

    Thread serverThread;
    MessageBus bus;
    
    @Test
    public void testMessageBusCanBeInitializedWithPortNumber() throws Exception {
        MocketMessageBus bus = new MocketMessageBus(5000);
        assertEquals(5000, bus.getPortNumber());
    }

    @Test
    public void testMessageBusCanBeStarted() throws Exception {
        startPrivateServer(5000);
        Socket clientSocket = new Socket(InetAddress.getLocalHost(), 5000);
        assertTrue(clientSocket.isConnected());
    }


    @Test
    public void testMessageBusCanWriteData() throws Exception {
        startPrivateServer(5000);
        Socket serverSocket = new Socket(InetAddress.getLocalHost(), 5000);
        Thread.sleep(1000);
        bus.writeData("This is a Test");
        BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
        assertEquals("This is a Test", in.readLine());
    }

    @Test
    public void testBusAutoCloseSockets() throws Exception {
        startPrivateServer(5000);
        bus.close();
        bus.close();
    }

    private void startPrivateServer(final int portNumber) throws InterruptedException {
        bus = new SocketMessageBus(portNumber);
        serverThread = new Thread() {
            public void run() {
                try {
                     bus.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        serverThread.start();
    }

    @After
    public void tearDown() throws IOException {
        if ( bus != null ) {
            bus.close();
        }
    }
}
