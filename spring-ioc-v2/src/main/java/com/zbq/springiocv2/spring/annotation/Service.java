package com.zbq.springiocv2.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {

    String value() default "";
}
