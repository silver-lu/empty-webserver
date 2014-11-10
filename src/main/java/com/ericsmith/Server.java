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
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                out.println("Server Up");
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
