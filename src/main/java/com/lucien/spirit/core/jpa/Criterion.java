package com.lucien.spirit.core.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 条件接口 用户提供条件表达式接口
 * 
 * @Filename : Criterion.java
 * @Package : com.lucien.spirit.core.jpa
 * @Description : Lucien基础服务平台
 * @author : lijunf
 * @CreateDate : 2016年2月1日
 */
public interface Criterion {
    public enum Operator {
        EQ, NE, LIKE, GT, LT, GTE, LTE, AND, OR
    }

    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder);
}
