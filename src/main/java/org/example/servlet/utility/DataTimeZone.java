package org.example.servlet.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public final class DataTimeZone {

    private DataTimeZone() {
    }

    public static String getDataTimeZone() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date());
    }

    public static String getDataTimeZone(final String timeZoneParameter) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z" + timeZoneParameter);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date());
    }
}
