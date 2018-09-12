package com.zbq.springiocv1.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhangboqing
 * @date 2018/7/10
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
}
