package com.rainbow.common.annotation;

import com.sun.org.apache.regexp.internal.RE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Author:liuhao
 * @Date:2019/4/28 17:59
 * @Description:
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value() default "";
}
