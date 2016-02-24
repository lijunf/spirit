package com.lucien.spirit.core.util;

import com.lucien.spirit.core.cache.CacheLoader;

/**
 * 系统参数帮助类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午4:43:26
 * <p>Version: 1.0
 */
public class ConfigUtil {
    
    /**
     * 获取系统参数值.
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
