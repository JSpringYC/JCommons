package com.jiangyc.jcommons.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期格式化工具类
 */
public class DateFormats {
    /** 默认的日期格式化 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认的时间格式化 */
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date time) {
        return format(time, null);
    }

    public static String format(Date time, String format) {
        format = Strings.isBlank(format) ? DEFAULT_DATE_FORMAT : format;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(time);
    }

    public static String format(long time) {
        return format(time, null);
    }

    public static String format(long time, String format) {
        format = Strings.isBlank(format) ? DEFAULT_TIME_FORMAT : format;

        return format(new Date(time), format);
    }

    public static String format(Calendar time) {
        return format(time, null);
    }

    public static String format(Calendar time, String format) {
        format = Strings.isBlank(format) ? DEFAULT_DATE_FORMAT : format;

        return time == null ? null : format(time.getTime(), format);
    }
}
