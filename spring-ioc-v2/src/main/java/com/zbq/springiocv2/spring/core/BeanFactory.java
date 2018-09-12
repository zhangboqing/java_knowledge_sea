package com.zbq.springiocv2.spring.core;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
public interface BeanFactory {

    /**
     * 根据beanName从IOC容器之中获得一个实例Bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);
}
