package com.lucien.spirit.core.security.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记此注解则会验证TOKEN
 * @Filename : TokenValid.java
 * @Package : com.lucien.spirit.core.security.annotation
 * @Description : TODO
 * @author : lijunf
 * @CreateDate : 2016年2月14日
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenValid {

}

