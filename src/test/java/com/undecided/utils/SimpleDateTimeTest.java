package com.undecided.utils;

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

}
