package org.example.servlet.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DataTimeZone {

    private DataTimeZone() {
    }

    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(new Date());
    }

    public static String getCurrentUtcTime(final String timeZoneParameter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z" + timeZoneParameter);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

        return sdf.format(new Date());
    }

    public static String parseTimeZone(final String timeZoneQueryString) {
        if (timeZoneQueryString.length() > 12 && timeZoneQueryString.length() < 15) {
            return timeZoneQueryString.substring(12, 14);
        } else if ((timeZoneQueryString.length() > 12 && timeZoneQueryString.length() < 16)) {
            return timeZoneQueryString.substring(12, 15);
        }

        return "";
    }
}
