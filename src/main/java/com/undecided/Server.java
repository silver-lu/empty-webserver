package com.undecided;

public class Server {

    public static void main(String[] args) throws Exception {
        MessageBus bus = new SocketMessageBus(5000);
        bus.start();
        String input = bus.readData();
        String res = processRequest(input);
        bus.writeData(res);
    }

    public static String processRequest(String input) {
        if (input.equals("Test")) {
            return new String(input);
        }
        else {
            return new String("HTTP/1.1 404 Not Found");
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
