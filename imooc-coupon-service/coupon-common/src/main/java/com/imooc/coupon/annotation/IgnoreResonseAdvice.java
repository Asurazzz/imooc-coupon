package com.imooc.coupon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname IgnoreResonseAdvice
 * @Description 忽略统一响应注解定义
 * @Date 2021/7/6 19:57
 * @Created by yemingjie
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResonseAdvice {

}
