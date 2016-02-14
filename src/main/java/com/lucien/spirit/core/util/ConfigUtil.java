package com.lucien.spirit.core.util;

import com.lucien.spirit.core.cache.CacheLoader;

/**
 * 系统参数帮助类
 * @Filename : ConfigUtil.java
 * @Package : com.lucien.spirit.core.util
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月5日
 */
public class ConfigUtil {
    
    /**
     * 获取系统参数值
     * @param key
     * @return
     */
    public static String getValue(String key) {
        CacheLoader cacheLoader = WebContextUtil.getBean(CacheLoader.class);
        try {
            return cacheLoader.getConfig().get(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
