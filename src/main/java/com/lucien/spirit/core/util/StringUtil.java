package com.lucien.spirit.core.util;

public class StringUtil {

    /**
     * 判断字符串是否为空或空白字符
     * @param str
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
    
    /**
     * 判断字符串不为空和空白字符
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return str != null && str.trim().length() > 0;
    }
}
