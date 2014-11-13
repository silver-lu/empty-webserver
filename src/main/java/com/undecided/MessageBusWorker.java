package com.undecided;

import java.io.*;
import java.net.Socket;

/**
 * Created by silver.lu on 11/12/14.
 */
public class MessageBusWorker implements Runnable {
    private Socket socket;
    private RequestHandler handler;

    public MessageBusWorker(Socket socket, RequestHandler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    @Override
    public void run() {
        SocketMessageBus.activeSockets.add(socket);
        try {
            String input = readData();
            handler.setRequest(input);
            handler.processRequest();
            writeData(handler.getResponse());
            socket.close();
            SocketMessageBus.activeSockets.remove(socket);
        }
        catch (Exception e) {

        }

    }

    private String readData() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        return reader.readLine();
    }

    private void writeData(String input) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(input);
        writer.flush();
    }
}
