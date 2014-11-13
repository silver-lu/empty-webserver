package com.undecided.utils;

import com.undecided.exceptions.CommandLineArgumentNotFoundException;
import com.undecided.utils.CommandParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by silver.lu on 11/12/14.
 */
public class CommandParserTest {
    @Test
    public void testParserAcceptsDashPAsPortNumber() throws Exception {

        String[] args = new String[]{ "-p", "8080"};
        CommandParser commandParser = new CommandParser(args);
        commandParser.parse();
        assertEquals("8080", commandParser.getValueOf("-p"));
    }

    @Test
    public void testParserAcceptsDashDAsStartDirectory() throws Exception {
        String[] args = new String[]{ "-d", "abc"};
        CommandParser commandParser = new CommandParser(args);
        commandParser.parse();
        assertEquals("abc", commandParser.getValueOf("-d"));
    }

    @Test(expected = CommandLineArgumentNotFoundException.class)
    public void testExceptionIsThrownForNonExistingArgument() throws Exception {
        String[] args = new String[]{ "-d", "abc"};
        CommandParser commandParser = new CommandParser(args);
        commandParser.parse();
        commandParser.getValueOf("-p");
    }
}
