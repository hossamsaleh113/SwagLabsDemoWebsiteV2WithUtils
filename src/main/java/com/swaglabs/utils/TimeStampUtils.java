package com.swaglabs.utils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeStampUtils {
    private TimeStampUtils(){
        super();
    }

    public static String getTimeStamp(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        return formatter.format(date);
    }
}
