package com.lucien.spirit.core.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.lucien.spirit.core.constants.DictConstants;
import com.lucien.spirit.core.util.WebContextUtil;
import com.lucien.spirit.module.system.dao.ConfigDao;
import com.lucien.spirit.module.system.dao.DictTypeDao;
import com.lucien.spirit.module.system.model.Config;
import com.lucien.spirit.module.system.model.DictEntry;
import com.lucien.spirit.module.system.model.DictType;

/**
 * 缓存加载类.
 * <p>User: lijunf
 * <p>Date: 2016年2月24日 下午3:43:33
 * <p>Version: 1.0
 */
public class CacheLoader {

    /**
     * 日志对象.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 字典缓存.
     * @return key:缓存类型,value:字典常量bean
     * @throws Exception if has error(系统异常)
     */
    @Cacheable("dict")
    public Map<String, Map<String, String>> getDict() throws Exception {
        DictTypeDao dictTypeDao = WebContextUtil.getBean(DictTypeDao.class);
        logger.debug("正在初始化缓存: dict");

        List<DictType> types = dictTypeDao.findAll();
        Map<String, Map<String, String>> map = new LinkedHashMap<String, Map<String, String>>();

        for (DictType type : types) {
            Map<String, String> value = map.get(type.getDictTypeId());
            if (value == null) {
                value = new LinkedHashMap<String, String>();
            } else {
                map.put(type.getDictTypeId(), value);
            }
            List<DictEntry> entrys = type.getDictEntrys();
            for (DictEntry entry : entrys) {
                if (entry.getStatus() == DictConstants.STATUS_ENABLE) {
                    value.put(entry.getDictId(), entry.getDictName());
                }
            }
        }
        return map;
    }

    /**
     * 参数配置缓存.
     * @return Map<String, String>
     * @throws Exception if has error(系统异常)
     */
    @Cacheable("config")
    public Map<String, String> getConfig() throws Exception {
        ConfigDao configDao = WebContextUtil.getBean(ConfigDao.class);
        logger.debug("正在初始化缓存: config");

        List<Config> list = configDao.findAll();
        Map<String, String> map = new LinkedHashMap<String, String>();

        for (Config config : list) {
            map.put(config.getKey(), config.getValue());
        }
        return map;
    }

}
