package com.undecided;

import com.undecided.exceptions.CommandLineArgumentNotFoundException;

public class Server {
    public static String startDirectory;
    public static Integer listeningPort;


    public static void main(String[] args) throws Exception {

        setInitiationParams(args);
        MessageBus bus = new SocketMessageBus(listeningPort);
        bus.start();
        String input = bus.readData();
        String res = processRequest(input);
        bus.writeData(res);
    }

    private static void setInitiationParams(String[] args) {
        CommandParser commandParser = new CommandParser(args);
        commandParser.parse();

        try {
            startDirectory = commandParser.getValueOf(ServerParamConstant.START_DIRECTORY);
        }
        catch (CommandLineArgumentNotFoundException e) {
            startDirectory = ServerParamConstant.DEFAULT_START_DIRECTORY;
        }

        try {
            listeningPort = Integer.parseUnsignedInt(commandParser.getValueOf(ServerParamConstant.PORT_NUMBER));
        }
        catch (CommandLineArgumentNotFoundException e) {
            listeningPort = ServerParamConstant.DEFAULT_PORT_NUMBER;
        }
    }



    public static String processRequest(String input) {

        RequestHandler handler = new RequestHandler(input);
        handler.processRequest();
        return handler.getResponse();
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
