package com.undecided.utils;

import com.undecided.exceptions.CommandLineArgumentNotFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silver.lu on 11/12/14.
 */
public class CommandParser {
    private String[] commandArgs;
    private Map<String, String> commandMap;


    public CommandParser(String[] commandArgs) {
       this.commandArgs = commandArgs;
       this.commandMap = new HashMap<String,String>();
    }

    public void parse() {
        for (int index = 0; index < commandArgs.length; index+=2) {
           commandMap.put(commandArgs[index], commandArgs[index + 1]);
        }
    }

  /*  private boolean isKey(int index) {
        if ( commandArgs[index].charAt(0) == '-' ) {
            return true;
        }
        return false;
    }*/

    public String getValueOf(String key) throws CommandLineArgumentNotFoundException {
        if ( commandMap.containsKey(key) ) {
            return commandMap.get(key);
        }
        else {
            throw new CommandLineArgumentNotFoundException();
        }
    }
}
