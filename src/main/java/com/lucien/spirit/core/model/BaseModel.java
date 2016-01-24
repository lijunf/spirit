package com.lucien.spirit.core.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实体基类,重写toString,hashCode,equals方法.
 * 
 * @Filename : BaseModel.java
 * @Package : com.lucien.spirit.core.model
 * @Description : Lucien基础服务平台
 * @author : lijunf
 * @CreateDate : 2016年1月21日
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 7369795311057059048L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
