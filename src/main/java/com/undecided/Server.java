package com.undecided;

import com.undecided.constants.Configurations;
import com.undecided.constants.ServerParamConstant;
import com.undecided.exceptions.CommandLineArgumentNotFoundException;
import com.undecided.utils.CommandParser;

public class Server {
    public static String startDirectory;
    public static Integer listeningPort;
    public static Configurations configuration;


    public static void main(String[] args) throws Exception {
        configuration = new Configurations();

        setInitiationParams(args);
        MessageBus bus = new SocketMessageBus(listeningPort);
        bus.start();
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
}
