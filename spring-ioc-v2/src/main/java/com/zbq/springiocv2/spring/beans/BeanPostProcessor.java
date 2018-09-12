package com.zbq.springiocv2.spring.beans;

/**
 * @author zhangboqing
 * @date 2018/7/15
 */
//用作事件监听
public class BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean,String beanName) {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean,String beanName) {
        return bean;
    }

}
