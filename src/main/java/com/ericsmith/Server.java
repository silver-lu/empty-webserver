package com.ericsmith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        MessageBus bus = new SocketMessageBus(5000);
        bus.start();
        String input = bus.readData();
        String res = processRequest(input);
        bus.writeData(res);
    }

    public static String processRequest(String input) throws Exception {
        try {
            ClientRequest request = new ClientRequest(input);
            if (request.getRequestUrl().equals("/ping")) {
                return new String("Pong");
            } else {
                return new String("HTTP/1.1 404 Not Found");
            }
        }
        catch ( RequestMethodNotRecognizedException expected) {
            return new String ("HTTP/1.1 405 Method Not Allowed");
        }
        catch ( Exception e) {
            return new String ("HTTP/1.1 400 Bad Request");
        }
    }

/*
    public void run() throws Exception {
        ServerSocket listener = new ServerSocket(this.portNumber);
        try {
            Socket clientSocket = listener.accept();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                try {
                    ClientRequest clientRequest = new ClientRequest(new InputStreamReader(clientSocket.getInputStream()));

                    if ( clientRequest.getRequestUrl().equals("/echo") ) {
                        out.println("echo");
                    }
                    else {
                        out.println("HTTP/1.1 404 Not Found");
                    }
                }
                catch (MalformedRequestException expected) {
                    out.println("HTTP/1.1 400 Bad Request");
                }


            }
            finally {
                clientSocket.close();
            }

        }
        finally {
            listener.close();
        }
    }

 */
}
