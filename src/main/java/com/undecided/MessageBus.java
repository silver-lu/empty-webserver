package com.undecided;

import java.io.IOException;

/**
 * Created by silver.lu on 11/10/14.
 */
public interface MessageBus {
    public void start() throws IOException;
    public void close() throws IOException;

}
