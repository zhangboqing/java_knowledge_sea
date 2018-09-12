package com.zbq.springiocv2.spring.beans;

import lombok.Data;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
//用来存储配置文件中的信息
//相当于保存在内存中的配置
@Data
public class BeanDefinition {

    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;
}
