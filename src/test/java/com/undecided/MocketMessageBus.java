package com.undecided;

/**
 * Created by silver.lu on 11/10/14.
 */
public class MocketMessageBus extends MessageBus {
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
    public void close() {

    }

    public int getPortNumber() {
        return portNumber;
    }
}
