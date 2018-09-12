package com.zbq.springiocv2.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {

    String value() default "";

    boolean required() default true;
}
