package com.undecided;

import com.undecided.mocks.MocketMessageBus;
import org.junit.After;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;

import static org.junit.Assert.*;

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
    public void testMessageBusCanHandleMultipleConnections() throws Exception {
        startPrivateServer(5000);

        Socket firstSocket = new Socket(InetAddress.getLocalHost(), 5000);
        PrintWriter firstOut = new PrintWriter(firstSocket.getOutputStream(), true);
        firstOut.println("This is a read Test");

        Socket secondSocket = new Socket(InetAddress.getLocalHost(), 5000);
        PrintWriter secondOut = new PrintWriter(secondSocket.getOutputStream(), true);
        secondOut.println("GET /foo HTTP/1.1");

        // read from second socket
        BufferedReader secondIn = new BufferedReader(new InputStreamReader(secondSocket.getInputStream()));
        assertEquals("HTTP/1.1 404 Not Found", secondIn.readLine());

        // read from first socket
        BufferedReader firstIn = new BufferedReader(new InputStreamReader(firstSocket.getInputStream()));
        assertEquals("HTTP/1.1 400 Bad Request", firstIn.readLine());
    }


    @Test (expected = ConnectException.class)
    public void testWeAreAbleToShutDownServerSocketWithClose() throws Exception {
        startPrivateServer(5000);
        Thread.sleep(500);
        bus.close();
        Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
    }

    @Test
    public void testEndToEndWeGetA400BackWithABadRequest() throws Exception {
        startPrivateServer(5000);
        Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("This is a read Test");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        assertEquals("HTTP/1.1 400 Bad Request", in.readLine());
    }



    /*
    @Test
    public void testMessageBusCanWriteData() throws Exception {
        startPrivateServer(5000);
        Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
        Thread.sleep(1000);
        bus.writeData("This is a write Test");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        assertEquals("This is a write Test", in.readLine());
    }
/*
    @Test
    public void testMessageBusCanReadData() throws Exception {
        startPrivateServer(5000);
        Socket socket = new Socket(InetAddress.getLocalHost(), 5000);
        Thread.sleep(1000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println("This is a read Test");
        assertEquals("This is a read Test", bus.readData());
    }
*/

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
