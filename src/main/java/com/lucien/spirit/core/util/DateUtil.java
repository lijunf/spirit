package com.lucien.spirit.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lucien.spirit.core.constants.FormatConstant;

public class DateUtil {
    
    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        String sDate = null;
        if (date != null) {
            if (pattern == null)
                pattern = FormatConstant.DEFALUT_DATE_PATTERN;
            
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            sDate = formatter.format(date);
        }
        return sDate;
    }

    /**
     * 根据默认日期格式yyyy-MM-dd格式化
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date, FormatConstant.DEFALUT_DATE_PATTERN);
    }
    
    /**
     * 将字符串转化为日期
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parseDate(String dateStr, String pattern) {
        if (dateStr != null && dateStr.trim().length() > 0) {
            try {
                DateFormat format = new SimpleDateFormat(pattern);
                Date date = format.parse(dateStr);
                return date;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
    
    /**
     * 将字符串转化为日期，默认日期格式yyyy-MM-dd
     * @param dateStr
     * @return
     */
    public static Date parseDate(String dateStr) {
        return parseDate(dateStr, FormatConstant.DEFALUT_DATETIME_PATTERN);
    }
}
