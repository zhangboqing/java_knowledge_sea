package com.zbq.springiocv1.spring.annotation;

import java.lang.annotation.*;

/**
 * @author zhangboqing
 * @date 2018/7/9
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Controller {

    String value() default "";
}
