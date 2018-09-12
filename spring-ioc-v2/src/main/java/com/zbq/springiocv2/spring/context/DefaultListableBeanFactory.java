package com.zbq.springiocv2.spring.context;

import com.zbq.springiocv2.spring.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangboqing
 * @date 2018/7/14
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    //beanDefinitionMap用来保存配置信息
    protected Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected void onRefresh() {
    }

    @Override
    protected void refreshBeanFactory() {

    }
}
