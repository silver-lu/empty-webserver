package com.undecided;

import java.io.IOException;

/**
 * Created by silver.lu on 11/10/14.
 */
public abstract class MessageBus {
    public abstract void start() throws IOException;
    public abstract void close() throws IOException;
    public boolean isRunning;
}
