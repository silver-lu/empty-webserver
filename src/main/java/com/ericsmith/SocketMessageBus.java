package com.ericsmith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by silver.lu on 11/10/14.
 */
public class SocketMessageBus implements MessageBus{

    private int portNumber;
    private ServerSocket listener;
    private Socket clientSocket;

    public SocketMessageBus(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void start() throws IOException {
        try {
            listener = new ServerSocket(this.portNumber);
            clientSocket = listener.accept();
        } catch (Exception e) {
            close();
        }
    }


    @Override
    public String readData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return reader.readLine();
    }

    @Override
    public void writeData(String input) throws IOException {
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
        writer.println(input);
        writer.flush();
    }

    @Override
    public void close() throws IOException {
        if ( listener != null ) {
            listener.close();
        }
        if ( clientSocket != null ) {
            clientSocket.close();
        }
    }
}
