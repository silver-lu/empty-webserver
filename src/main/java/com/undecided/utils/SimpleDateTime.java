package com.undecided.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by silver.lu on 11/11/14.
 */
public class SimpleDateTime implements SimpleDateTimeInterface {
    String dateTime = "";

    @Override
    public String now() {

        if (!dateTime.isEmpty())
            return dateTime;

        String DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTimeString = sdf.format(new Date());

        return dateTimeString;
    }

    @Override
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
