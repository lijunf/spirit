package com.lucien.spirit.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;

/**
 * 缓存清理帮助类，注意方法命名规范
 * @Filename : CacheEvictor.java
 * @Package : com.lucien.spirit.core.cache
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月5日
 */
public class CacheEvictor {
    
    private static final Logger logger = LoggerFactory.getLogger(CacheLoader.class);

    @CacheEvict(value="dict", allEntries=true)
    public void clearDict() {
        logger.info("清除字典缓存成功！");
    }
    
    @CacheEvict(value="config", allEntries=true)
    public void clearConfig() {
        logger.info("清除系统参数缓存成功！");
    }
    
}