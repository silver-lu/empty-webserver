package com.undecided;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by silver.lu on 11/10/14.
 */
public class SocketMessageBus extends MessageBus{

    private int portNumber;
    private ServerSocket listener;
    private Socket clientSocket;

    static Collection<Socket> activeSockets = new ConcurrentLinkedQueue<Socket>();

    public SocketMessageBus(int portNumber) {
        this.portNumber = portNumber;
    }

    public void start() throws IOException {
        try {
            listener = new ServerSocket(this.portNumber);
            isRunning = true;
            while ( isRunning ) {
                clientSocket = listener.accept();
                MessageBusWorker worker = new MessageBusWorker(clientSocket, new RequestHandler());
                new Thread(worker).start();
            }
        } catch (Exception e) {
        }
        finally {
            close();
        }
    }


    public void close() throws IOException {
        isRunning = false;
        if (listener != null) {
            listener.close();
        }
    }
}
