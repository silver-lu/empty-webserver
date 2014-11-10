package com.ericsmith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static String SERVER_START_COMMAND;
    private int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
        server.run();

    }

    public int getPort() {
        return portNumber;
    }

    public void run() throws IOException {
        ServerSocket listener = new ServerSocket(this.portNumber);
        try {
            Socket clientSocket = listener.accept();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                String request = in.readLine();
                String[] requestParam = request.split(" ");
                if ( requestParam.length > 2 && requestParam[1].equals("/echo") ) {
                    out.println("echo");
                }
                else if ( requestParam.length > 2 && requestParam[1].equals("/foobar")){
                    out.println("HTTP/1.1 404 Not Found");
                }
                else {
                    out.println("Server Up");
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
}
