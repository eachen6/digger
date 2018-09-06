package com.digger.utils;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String formatTime(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    
    public static String formatTime2(String format, Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(time);
    }
}
