package com.undecided;

import com.undecided.responses.ServerResponse;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

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
            e.printStackTrace();
        }
    }

    private String readData() throws Exception {
        InputStream inputStream = socket.getInputStream();
        StringBuilder sb = new StringBuilder();
        byte[] b = new byte[255];

        // Such a bad hack
        Thread.sleep(50);
        while (inputStream.available() > 0) {
            int length = inputStream.read(b);
            sb.append(new String(Arrays.copyOfRange(b, 0, length), "UTF-8"));
        }

        return sb.toString();
    }

    private void writeData(ServerResponse response) throws IOException {
        writeStringData(response.getHeader());
        writeBinaryData(response.getBody());
    }

    private void writeBinaryData(byte[] data) throws IOException {
        OutputStream stream = socket.getOutputStream();
        stream.write(data);
        stream.flush();
    }


    private void writeStringData(String input) throws IOException {
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(input);
        writer.flush();
    }
}
