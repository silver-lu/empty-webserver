package com.ericsmith;

/**
 * Created by silver.lu on 11/10/14.
 */
public class MocketMessageBus implements MessageBus {
    private int portNumber;
    public boolean started;

    public MocketMessageBus(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void start() {
        started = true;
    }

    @Override
    public String readData() {
        return new String("404");
    }

    @Override
    public void writeData(String input) {

    }

    @Override
    public void close() {

    }

    public int getPortNumber() {
        return portNumber;
    }
}
