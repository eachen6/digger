package com.digger.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToString {

    public static String getDateString(String format, Date ts) {
        if(ts==null||format==null)
            return null;
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(ts);
    }
}
