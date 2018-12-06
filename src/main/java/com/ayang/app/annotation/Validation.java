package com.ayang.app.annotation;

import javax.validation.groups.Default;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * entity属性验证
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Validation {

    /**
     * 验证分组
     */
    public Class value() default Default.class;//默认的认证规则

    /**
     * 验证失败以后的页面
     */
    public String error(); //如果失败以后返回的页面

}
