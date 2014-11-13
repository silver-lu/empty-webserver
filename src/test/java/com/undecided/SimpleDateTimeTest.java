package com.undecided;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by anthony.chai on 11/13/14.
 */
public class SimpleDateTimeTest {

    @Test
    public void nowReturnsGMTDate() {
        SimpleDateTime dateTime = new SimpleDateTime();
        String date = dateTime.now();

        assertTrue(date.contains("GMT"));
    }

    @Test
    public void nowReturnsWordDate() {
        SimpleDateTime dateTime = new SimpleDateTime();
        String date = dateTime.now();

        assertTrue(date.contains("Date: "));
    }
}
