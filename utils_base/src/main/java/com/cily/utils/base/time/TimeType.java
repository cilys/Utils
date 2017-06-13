package com.cily.utils.base.time;

/**
 * user:cily
 * time:2017/6/13
 * desc:
 */

public interface TimeType {
    String DAY = "yyyyMMdd";
    String HOUR = "yyyyMMddHH";
    String MINUTE = "yyyyMMddHHmm";
    String SECOND = "yyyyMMddHHmmss";

    String DAY_LINE = "yyyy-MM-dd";
    String HOUR_LINE = "yyyy-MM-dd-HH";
    String MINUTE_LINE = "yyyy-MM-dd-HH-mm";
    String SECOND_LINE = "yyyy-MM-dd-HH-mm-ss";

    String DAY_LINE_UNDER = "yyyy_MM_dd";
    String HOUR_LIND_UNDER = "yyyy_MM_dd_HH";
    String MINUTE_LIND_UNDER = "yyyy_MM_dd_HH_mm";
    String SECOND_LINE_UNDER = "yyyy_MM_dd_HH_mm_ss";

    String MINUTE_LINE_COLON = "yyyy-MM-dd HH:mm";
    String SECONDLINE_COLON = "yyyy-MM-dd HH:mm:ss";
}
