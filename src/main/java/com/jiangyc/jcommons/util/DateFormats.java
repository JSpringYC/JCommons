package com.jiangyc.jcommons.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期格式化工具类
 * 格式化说明：
 *  yyyy: 年份，如2018
 *  MM: 月份，如01
 *  dd: 日期
 *  HH: 24小时制的时
 *  hh: 12小时制的时
 *  mm: 分
 *  ss: 秒
 *  sss: 毫秒
 */
public class DateFormats {
    /** 默认的日期格式化 */
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /** 默认的时间格式化 */
    public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss sss";
    /** 一个Map，用作缓存以提高效率. */
    private static final Map<String, SimpleDateFormat> FORMAT_MAP = new HashMap<>();

    static {
        // 日期
        FORMAT_MAP.put(DEFAULT_DATE_FORMAT, new SimpleDateFormat(DEFAULT_DATE_FORMAT));
        // 时间
        FORMAT_MAP.put(DEFAULT_TIME_FORMAT, new SimpleDateFormat(DEFAULT_TIME_FORMAT));
    }

    public static SimpleDateFormat newDateFormat(String format) {
        format = Strings.isBlank(format) ? DEFAULT_TIME_FORMAT : format;

        SimpleDateFormat dateFormat = FORMAT_MAP.get(format);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(format);
            FORMAT_MAP.put(format, dateFormat);
        }

        return dateFormat;
    }

    public static String format(Date time, String format) {
        return newDateFormat(format).format(time);
    }

    public static String format(long time, String format) {
        return format(new Date(time), format);
    }

    public static String format(Calendar time, String format) {
        return time == null ? null : format(time.getTime(), format);
    }
}
